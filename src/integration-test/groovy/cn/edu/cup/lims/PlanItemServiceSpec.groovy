package cn.edu.cup.lims

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PlanItemServiceSpec extends Specification {

    PlanItemService planItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PlanItem(...).save(flush: true, failOnError: true)
        //new PlanItem(...).save(flush: true, failOnError: true)
        //PlanItem planItem = new PlanItem(...).save(flush: true, failOnError: true)
        //new PlanItem(...).save(flush: true, failOnError: true)
        //new PlanItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //planItem.id
    }

    void "test get"() {
        setupData()

        expect:
        planItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PlanItem> planItemList = planItemService.list(max: 2, offset: 2)

        then:
        planItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        planItemService.count() == 5
    }

    void "test delete"() {
        Long planItemId = setupData()

        expect:
        planItemService.count() == 5

        when:
        planItemService.delete(planItemId)
        sessionFactory.currentSession.flush()

        then:
        planItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PlanItem planItem = new PlanItem()
        planItemService.save(planItem)

        then:
        planItem.id != null
    }
}
