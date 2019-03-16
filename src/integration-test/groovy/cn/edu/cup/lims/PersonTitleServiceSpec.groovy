package cn.edu.cup.lims

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PersonTitleServiceSpec extends Specification {

    PersonTitleService personTitleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PersonTitle(...).save(flush: true, failOnError: true)
        //new PersonTitle(...).save(flush: true, failOnError: true)
        //PersonTitle personTitle = new PersonTitle(...).save(flush: true, failOnError: true)
        //new PersonTitle(...).save(flush: true, failOnError: true)
        //new PersonTitle(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //personTitle.id
    }

    void "test get"() {
        setupData()

        expect:
        personTitleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PersonTitle> personTitleList = personTitleService.list(max: 2, offset: 2)

        then:
        personTitleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        personTitleService.count() == 5
    }

    void "test delete"() {
        Long personTitleId = setupData()

        expect:
        personTitleService.count() == 5

        when:
        personTitleService.delete(personTitleId)
        sessionFactory.currentSession.flush()

        then:
        personTitleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PersonTitle personTitle = new PersonTitle()
        personTitleService.save(personTitle)

        then:
        personTitle.id != null
    }
}
