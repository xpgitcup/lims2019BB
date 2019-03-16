package cn.edu.cup.lims

/*
*  具体的任务（科研项目、教学任务--的分类都应该是叶子）
* */
class ThingType {

    String name
    ThingType upType

    static hasMany = [subTypes: ThingType, things: Thing]

    static constraints = {
        name(unique: true)
        upType(nullable: true)
    }

    static mapping = {
        sort: 'id'
        subTypes sort: 'id'  //这是排序的标准做法
        things sort: 'id'
    }

    String toString() {
        return "${name}"
    }

    List relatedThingTypeList() {
        def list = []
        if (subTypes) {
            subTypes.each { e->
                list.addAll(e.relatedThingTypeList())
            }
        } else {
            list.add(this)
        }
        return list
    }

    boolean bePartOf(ThingType athingType) {
        boolean isThis = athingType.equals(this)
        //boolean isMember = subTypes.contains(athingType)
        boolean isMember = relatedThingTypeList().contains(athingType)
        return (isThis || isMember)
    }

}
