package micronaut.data.update.many.to.one

import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class PersonRepositorySpec extends Specification {

    @Inject
    PersonRepository personRepository
    @Inject
    AddressRepository addressRepository

    def "test save and update"(){
        given:
        Address address = new Address(name: "foo")

        and:
        address = addressRepository.save(address)

        and:
        Person person = new Person(name: "Goo", address:  address)

        when:
        person = personRepository.save(person)

        then:
        person
        person.id

        when:
        person.name = "Noo"
        person = personRepository.update(person)
        def person2 = personRepository.findById(person.id).get()

        then:
        person2.name == "Noo"
    }
}
