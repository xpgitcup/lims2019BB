package cn.edu.cup.lims

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProjectPlanItemServiceSpec extends Specification {

    ProjectPlanItemService projectPlanItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProjectPlanItem(...).save(flush: true, failOnError: true)
        //new ProjectPlanItem(...).save(flush: true, failOnError: true)
        //ProjectPlanItem projectPlanItem = new ProjectPlanItem(...).save(flush: true, failOnError: true)
        //new ProjectPlanItem(...).save(flush: true, failOnError: true)
        //new ProjectPlanItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //projectPlanItem.id
    }

    void "test get"() {
        setupData()

        expect:
        projectPlanItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProjectPlanItem> projectPlanItemList = projectPlanItemService.list(max: 2, offset: 2)

        then:
        projectPlanItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        projectPlanItemService.count() == 5
    }

    void "test delete"() {
        Long projectPlanItemId = setupData()

        expect:
        projectPlanItemService.count() == 5

        when:
        projectPlanItemService.delete(projectPlanItemId)
        sessionFactory.currentSession.flush()

        then:
        projectPlanItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProjectPlanItem projectPlanItem = new ProjectPlanItem()
        projectPlanItemService.save(projectPlanItem)

        then:
        projectPlanItem.id != null
    }
}
