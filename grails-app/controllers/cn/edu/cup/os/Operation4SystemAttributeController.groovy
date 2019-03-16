package cn.edu.cup.os

import cn.edu.cup.system.JsFrame
import cn.edu.cup.system.SystemAttribute
import cn.edu.cup.system.SystemAttributeController
import grails.converters.JSON

class Operation4SystemAttributeController extends SystemAttributeController {

    def treeViewService
    /*
    * 创建对象
    * */
    def createSystemAttribute(SystemAttribute systemAttribute) {
        def newSystemAttribute = new SystemAttribute(upAttribute: systemAttribute)
        if (request.xhr) {
            render(template: 'create', model: [systemAttribute: newSystemAttribute])
        } else {
            respond newSystemAttribute
        }
    }

    /*
    * 获取json格式的树形结构数据
    * */

    def getTreeViewData() {
        def data = SystemAttribute.findAllByUpAttributeIsNull(params);
        params.context = "name"
        params.subItems = "subAttribues"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
