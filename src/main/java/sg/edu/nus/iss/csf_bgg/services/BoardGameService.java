package sg.edu.nus.iss.csf_bgg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.csf_bgg.models.Result;
import sg.edu.nus.iss.csf_bgg.repositories.BoardGameRepo;

@Service
public class BoardGameService {
    
    @Autowired
    BoardGameRepo bgRepo;

    public Optional<List<Result>> findByName(String name){

        // check result is present and convert to json format

        return bgRepo.findByName(name);
        
    }

    
}
