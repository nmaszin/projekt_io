# SortingMadness - dokumentacja API

## Zapytanie

`GET /api/sort`

W ciele zapytania mogą znajdować się następujące pola:

- data - jest to tablica, która zawiera przynajmniej jeden element. Elementami mogą być: liczby, napisy lub obiekty, ale wszystkie elementy muszą być tego samego typu (wszystkie elementy muszą być liczbami albo wszystkie elementy muszą być napisami albo wszystkie elementy muszą być obiektami) - **pole obowiązkowe**
- key - jest to napis, który definiuje atrybut obiektu, po którym sortowane są obiekty. Pole to nie może być zdefiniowane w zapytaniu w przypadku sortowania liczb lub napisów, ale jest obowiązkowe w przypadku sortowania obiektów. Wymagane jest, aby wszystkie sortowane obiekty posiadały ten atrybut
- reverse - jest to wartość logiczna, która definiuje kolejność sortowania danych. Wartość `false` oznacza sortowanie rosnące, wartość `true` - sortowanie malejące. Domyślnie jest to `false`, czyli w przypadku niezdefiniowania tego atrybutu dane będą sortowane rosnąco. - **pole opcjonalne**
- algorithms - jest to niepusta tablica napisów, spośród których każdy jest poprawnym identyfikatorem danego algorytmu sortowania. Jeżeli pole nie zostało zdefiniowane, aplikacja sama dobierze algorytm. - **pole opcjonalne**

Przykładowe zapytanie:

```json
{
    "data": [
        { "a": 1, "b": 2 },
        { "a": 4, "b": 1 },
        { "a": 4, "b": 2 },
        { "a": 4, "b": 0 },
        { "a": 4, "c": 2 }
    ],
    "key": "a",
    "reverse": true,
    "algorithms": [
        "bubble",
        "insertion",
        "quick"
    ]
}
```

## Odpowiedź

W przypadku, gdy zapytanie użytkownika jest poprawne, aplikacja zwraca odpowiedź HTTP o kodzie **200 (OK)** w formacie JSON. Odpowiedź zawiera pole result, które jest obiektem. Każdy z atrybutów tego obiektu odpowiada wynikowi działania innego algorytmu.

Wynik działania algorytmu to posortowane dane oraz czas trwania algorytmu.

Przykładowa odpowiedź:

```json
{
    "result": {
        "bubble": {
            "data": [1, 2, 3],
            "time": 0.997
        },
        "insertion": {
            "data": [1, 2, 3],
            "time": 1.112
        }
    }
}
```

W przypadku błędnego zapytania zwracana jest odpowiedź HTTP **400 (Bad request)** z komunikatem w polu `message`, np.:

```json
{
    "message": "Data array is empty"
}
```

W przypadku gdy zapytanie odwołuje się do niepoprawnego adresu zwracany jest **HTTP 404 (Not found)**.

## Potencjalne błędy

- Pusta lista `data` lub pole to nie jest listą, albo nie zostało w ogóle zdefiniowane
- Niespójność typów danych w liście `data` (wyrzucamy błąd gdy: liczba + tekst, liczba + obiekt, obiekt + tekst)
- Gdy zdefiniowano `key`, a nie sortujemy po obiektach lub w którymś z obiektów brakuje tego atrybutu
- Gdy zdefiniowano `key`, ale nie jest on napisem
- Gdy reverse nie jest wartością logiczną
- Gdy zdefiniowano `algorithms`, ale nie jest to lista lub jest to pusta lista, albo nie jest to lista napisów
- Gdy zdefiniowano `algorithms`, ale któryś z podanych algorytmów nie istnieje

