package jp.co.interprism.training

import spock.lang.Shared
import spock.lang.Specification

class HelloWorldSpec extends Specification {

    @Shared
            message = 'Hello world!'

    def "The world can say hello using when and then"() {
        when:
        def newMessage = message
        then:
        newMessage == 'Hello world!'
    }

    def "The world can say hello using expect"() {
        expect:
        message == 'Hello world!'
    }

}
