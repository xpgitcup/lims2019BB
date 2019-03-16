package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemSessionKeyServiceSpec extends Specification {

    SystemSessionKeyService systemSessionKeyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemSessionKey(...).save(flush: true, failOnError: true)
        //new SystemSessionKey(...).save(flush: true, failOnError: true)
        //SystemSessionKey systemSessionKey = new SystemSessionKey(...).save(flush: true, failOnError: true)
        //new SystemSessionKey(...).save(flush: true, failOnError: true)
        //new SystemSessionKey(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemSessionKey.id
    }

    void "test get"() {
        setupData()

        expect:
        systemSessionKeyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemSessionKey> systemSessionKeyList = systemSessionKeyService.list(max: 2, offset: 2)

        then:
        systemSessionKeyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemSessionKeyService.count() == 5
    }

    void "test delete"() {
        Long systemSessionKeyId = setupData()

        expect:
        systemSessionKeyService.count() == 5

        when:
        systemSessionKeyService.delete(systemSessionKeyId)
        sessionFactory.currentSession.flush()

        then:
        systemSessionKeyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemSessionKey systemSessionKey = new SystemSessionKey()
        systemSessionKeyService.save(systemSessionKey)

        then:
        systemSessionKey.id != null
    }
}
