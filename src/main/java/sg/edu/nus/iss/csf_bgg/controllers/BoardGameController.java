package sg.edu.nus.iss.csf_bgg.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.csf_bgg.Exceptions.GameNotFoundException;
import sg.edu.nus.iss.csf_bgg.models.Result;
import sg.edu.nus.iss.csf_bgg.services.BoardGameService;

@RestController
@RequestMapping("/api/bgg")
public class BoardGameController {

    @Autowired
    BoardGameService bgSvc;
    
    // GET /api/bgg?name=NAME

    @GetMapping()
    public ResponseEntity<List<Result>> findByName(@RequestParam String name) throws GameNotFoundException{

        Optional<List<Result>> results = bgSvc.findByName(name);

        if(results.isPresent()){
            return new ResponseEntity<List<Result>>(results.get(), HttpStatus.OK);
        }

        throw new GameNotFoundException(name);

    }

    // GET /api/bgg/mongo?name=NAME
    @CrossOrigin(origins="*")
    @GetMapping("/mongo")
    public ResponseEntity<String> mongoFindByName(@RequestParam String name) throws GameNotFoundException{

        Optional<JsonObject> results = bgSvc.mongoFindByName(name);

        if(results.isPresent()){
            return new ResponseEntity<String>(results.get().toString(), HttpStatus.OK);
        }

        throw new GameNotFoundException(name);

    }
}
