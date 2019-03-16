package cn.edu.cup.lims

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ThingTypeCircleServiceSpec extends Specification {

    ThingTypeCircleService thingTypeCircleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ThingTypeCircle(...).save(flush: true, failOnError: true)
        //new ThingTypeCircle(...).save(flush: true, failOnError: true)
        //ThingTypeCircle thingTypeCircle = new ThingTypeCircle(...).save(flush: true, failOnError: true)
        //new ThingTypeCircle(...).save(flush: true, failOnError: true)
        //new ThingTypeCircle(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //thingTypeCircle.id
    }

    void "test get"() {
        setupData()

        expect:
        thingTypeCircleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ThingTypeCircle> thingTypeCircleList = thingTypeCircleService.list(max: 2, offset: 2)

        then:
        thingTypeCircleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        thingTypeCircleService.count() == 5
    }

    void "test delete"() {
        Long thingTypeCircleId = setupData()

        expect:
        thingTypeCircleService.count() == 5

        when:
        thingTypeCircleService.delete(thingTypeCircleId)
        sessionFactory.currentSession.flush()

        then:
        thingTypeCircleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ThingTypeCircle thingTypeCircle = new ThingTypeCircle()
        thingTypeCircleService.save(thingTypeCircle)

        then:
        thingTypeCircle.id != null
    }
}
