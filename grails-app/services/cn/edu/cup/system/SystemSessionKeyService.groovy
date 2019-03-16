package cn.edu.cup.system

import grails.gorm.services.Service

@Service(SystemSessionKey)
interface SystemSessionKeyService {

    SystemSessionKey get(Serializable id)

    List<SystemSessionKey> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemSessionKey save(SystemSessionKey systemSessionKey)

}