package com.rta.source.stream

import com.fasterxml.jackson.databind.ObjectMapper
import com.rta.source.dto.CatDto
import org.springframework.messaging.MessageChannel
import spock.lang.Specification

/**
 * Created by rparry on 2016-07-22.
 */
class CatSourceSpec extends Specification {

    CatSource catSource;

    def setup() {
        this.catSource = new CatSource();
        this.catSource.mapper = new ObjectMapper();
        this.catSource.post     = Mock(MessageChannel);
    }

    def "simple test for teh source"() {

        when:
        this.catSource.send([name: "bobo"] as CatDto);

        then:
        1 * this.catSource.post.send(_)
    }
}
