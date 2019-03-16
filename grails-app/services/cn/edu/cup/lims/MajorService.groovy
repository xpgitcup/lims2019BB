package cn.edu.cup.lims

import grails.gorm.services.Service

@Service(Major)
interface MajorService {

    Major get(Serializable id)

    List<Major> list(Map args)

    Long count()

    void delete(Serializable id)

    Major save(Major major)

}