package cn.edu.cup.system

class SystemSession {

    String sessionName

    static hasMany = [keys: SystemSessionKey]

    static constraints = {
    }

    String toString() {
        return sessionName
    }
}
