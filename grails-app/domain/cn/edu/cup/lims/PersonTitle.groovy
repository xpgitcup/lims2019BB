package cn.edu.cup.lims

class PersonTitle {

    String name
    PersonTitle upTitle

    static hasMany = [subTitles: PersonTitle, persons: Person]

    static constraints = {
        name(unique: true)
        upTitle(nullable: true)
    }

    static mapping = {
        sort: 'id'
        subTitles sort: 'id'  //这是排序的标准做法
        persons sort: 'id'
    }

    String toString() {
        return name
    }

    boolean bePartOfByName(String aTitleName) {
        def aTitle = PersonTitle.findByName(aTitleName)
        return bePartOf(aTitle)
    }

    boolean bePartOf(PersonTitle aTitle) {
        boolean isThis = aTitle.equals(this)
        boolean isMember = aTitle.subTitles.contains(this)
        return (isThis || isMember)
    }

    static def checkThingType() {
        def tlist = PersonTitle.findAllByRelatedThingTypeIsNull()
        tlist.each { e->
            if (e.upTitle) {
                e.relatedThingType = e.upTitle.relatedThingType
            } else {
                e.relatedThingType = ThingType.findByName("全部任务")
            }
        }
    }

}
