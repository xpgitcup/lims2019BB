package cn.edu.cup.lims

class ThingTypeCircle {

    ThingType thingType
    PersonTitle personTitle

    static constraints = {
        thingType()
        personTitle()
    }

    String toString() {
        return "${id}.${thingType}-${personTitle}"
    }

    static List allRelatedThingTypes(PersonTitle pt) {
        def list = []
        def p = pt
        while (p) {
            def ts = cn.edu.cup.lims.ThingTypeCircle.findAllByPersonTitle(p)
            ts.each { e ->
                list.addAll(e.thingType.relatedThingTypeList())
            }
            p = p.upTitle
        }
        def result = list.unique()
        return result
    }

}
