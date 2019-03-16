package cn.edu.cup.lims

import grails.gorm.services.Service

@Service(PlanItem)
interface PlanItemService {

    PlanItem get(Serializable id)

    List<PlanItem> list(Map args)

    Long count()

    void delete(Serializable id)

    PlanItem save(PlanItem planItem)

}