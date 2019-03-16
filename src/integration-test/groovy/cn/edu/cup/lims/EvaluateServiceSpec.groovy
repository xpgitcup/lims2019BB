package cn.edu.cup.lims

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EvaluateServiceSpec extends Specification {

    EvaluateService evaluateService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Evaluate(...).save(flush: true, failOnError: true)
        //new Evaluate(...).save(flush: true, failOnError: true)
        //Evaluate evaluate = new Evaluate(...).save(flush: true, failOnError: true)
        //new Evaluate(...).save(flush: true, failOnError: true)
        //new Evaluate(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //evaluate.id
    }

    void "test get"() {
        setupData()

        expect:
        evaluateService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Evaluate> evaluateList = evaluateService.list(max: 2, offset: 2)

        then:
        evaluateList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        evaluateService.count() == 5
    }

    void "test delete"() {
        Long evaluateId = setupData()

        expect:
        evaluateService.count() == 5

        when:
        evaluateService.delete(evaluateId)
        sessionFactory.currentSession.flush()

        then:
        evaluateService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Evaluate evaluate = new Evaluate()
        evaluateService.save(evaluate)

        then:
        evaluate.id != null
    }
}
