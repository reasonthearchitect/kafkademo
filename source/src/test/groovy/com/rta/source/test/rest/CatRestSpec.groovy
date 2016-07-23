package com.rta.source.test.rest

import com.rta.source.dto.CatDto
import com.rta.source.rest.CatRest
import com.rta.source.stream.CatSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification


/**
 * Created by rparry on 2016-07-22.
 */
class CatRestSpec extends Specification {

    CatRest catRest = new CatRest();

    def setup() {
        this.catRest        = new CatRest();
        this.catRest.source = Mock(CatSource);
    }

    def "simple test for the rest endpoint"() {

        when:
        ResponseEntity responseEntity = this.catRest.process([name: "bobo"] as CatDto);

        then:
        1 * this.catRest.source.send(_);
        responseEntity != null;
        responseEntity.getStatusCode() == HttpStatus.CREATED;
    }
}
