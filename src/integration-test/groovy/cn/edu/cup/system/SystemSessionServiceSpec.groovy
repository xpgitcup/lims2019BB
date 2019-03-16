package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemSessionServiceSpec extends Specification {

    SystemSessionService systemSessionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemSession(...).save(flush: true, failOnError: true)
        //new SystemSession(...).save(flush: true, failOnError: true)
        //SystemSession systemSession = new SystemSession(...).save(flush: true, failOnError: true)
        //new SystemSession(...).save(flush: true, failOnError: true)
        //new SystemSession(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemSession.id
    }

    void "test get"() {
        setupData()

        expect:
        systemSessionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemSession> systemSessionList = systemSessionService.list(max: 2, offset: 2)

        then:
        systemSessionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemSessionService.count() == 5
    }

    void "test delete"() {
        Long systemSessionId = setupData()

        expect:
        systemSessionService.count() == 5

        when:
        systemSessionService.delete(systemSessionId)
        sessionFactory.currentSession.flush()

        then:
        systemSessionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemSession systemSession = new SystemSession()
        systemSessionService.save(systemSession)

        then:
        systemSession.id != null
    }
}
