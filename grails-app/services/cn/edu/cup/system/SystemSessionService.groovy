package cn.edu.cup.system

import grails.gorm.services.Service

@Service(SystemSession)
interface SystemSessionService {

    SystemSession get(Serializable id)

    List<SystemSession> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemSession save(SystemSession systemSession)

}