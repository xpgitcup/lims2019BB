package cn.edu.cup.lims

import grails.gorm.services.Service

@Service(ProjectPlanItem)
interface ProjectPlanItemService {

    ProjectPlanItem get(Serializable id)

    List<ProjectPlanItem> list(Map args)

    Long count()

    void delete(Serializable id)

    ProjectPlanItem save(ProjectPlanItem projectPlanItem)

}