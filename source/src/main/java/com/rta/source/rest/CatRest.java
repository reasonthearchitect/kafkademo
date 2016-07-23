package com.rta.source.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rta.source.stream.CatSource;
import com.rta.source.dto.CatDto;

@RestController
@RequestMapping("/cats")
public class CatRest {

        @Autowired
        private CatSource source;


        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> process(@RequestBody CatDto catDto ) {
                source.send(catDto);

                HttpHeaders httpHeaders = new HttpHeaders();

                /* - Uncomment to add some additional headers.
                httpHeaders.setLocation(ServletUriComponentsBuilder
                                .fromCurrentRequest().path("/{id}")
                                .buildAndExpand(catDto.getId()).toUri());
                */
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
        }
}
