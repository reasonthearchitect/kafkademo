package com.rta.sink.test.stream

import com.fasterxml.jackson.databind.ObjectMapper
import com.rta.sink.dto.DogDto
import com.rta.sink.stream.SinkSink
import spock.lang.Specification

/**
 * Created by rparry on 2016-07-22.
 */
class SinkSinkSpec extends Specification {

    SinkSink sink;

    def setup() {
        this.sink           = new SinkSink();
        this.sink.mapper    = new ObjectMapper();
    }

    def "simple sink test" () {

        when:
        this.sink.sink([name: "Bobo", age: 1] as DogDto)

        then:
        1 == 1
    }
}
