package pl.put.poznan.sort.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sort.logic.SortHandler;
import pl.put.poznan.sort.logic.SortResult;


@RestController
@RequestMapping("/{text}")
public class SortController {

    private static final Logger logger = LoggerFactory.getLogger(SortController.class);


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public SortResult post(@PathVariable String text,
                           @Validated @RequestBody SortTask requestBody) {

        // log the parameters
        logger.debug(text);
        logger.debug(requestBody.toString());
        SortHandler sortHandler=new SortHandler(requestBody);
        SortResult result=sortHandler.run();
        return result;
    }



}


