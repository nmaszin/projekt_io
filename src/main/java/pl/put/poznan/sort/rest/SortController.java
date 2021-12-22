package pl.put.poznan.sort.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sort.logic.SortHandler;
import pl.put.poznan.sort.logic.SortResult;
import pl.put.poznan.sort.logic.SortTask;

import javax.validation.Valid;

/**
 * Main app controller
 */
@RestController
@RequestMapping("/sort")
public class SortController {
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    /**
     * Controller method which handles user sorting requests
     * @param task User's task which was sent in the request
     * @return App response
     */
    @PostMapping
    @ResponseBody
    public SortResult post(@Valid @RequestBody SortTask task) {
        SortHandler handler = new SortHandler(task);
        SortResult result = handler.run();
        return result;
    }
}
