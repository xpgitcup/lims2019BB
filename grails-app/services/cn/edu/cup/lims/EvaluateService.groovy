package cn.edu.cup.lims

import grails.gorm.services.Service

@Service(Evaluate)
interface EvaluateService {

    Evaluate get(Serializable id)

    List<Evaluate> list(Map args)

    Long count()

    void delete(Serializable id)

    Evaluate save(Evaluate evaluate)

}