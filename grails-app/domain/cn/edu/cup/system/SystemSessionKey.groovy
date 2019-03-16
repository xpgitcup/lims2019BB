package cn.edu.cup.system

class SystemSessionKey {

    String key
    String value

    static belongsTo = [systemSession: SystemSession]

    static constraints = {
    }

}
