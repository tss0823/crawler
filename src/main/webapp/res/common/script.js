/**
 * Created by shan on 8/13/2015.
 */


$(function () {

    jQuery.ajaxSettings.traditional = true;

    basePath = $("#basePath").val();

    $(".close").click(function(){
        $(".c-modal").hide();
    });




});



//获得提交表单数据
function getFormParams(){
    var paramsArray = $("form").serializeArray();
    var params = {};
    while(paramsArray.length > 0){
        var data = paramsArray.pop();
        var oldValue = params[data.name]
        if(typeof(oldValue) != "undefined"){  //存在数据
            if(typeof(oldValue) == "object"){
                oldValue.push(data.value); //add
            }else{
                var arr = new Array();
                arr.push(oldValue);
                arr.push(data.value);
                oldValue = arr;
            }
            params[data.name] = oldValue;
        }else{
            params[data.name] = data.value;
        }

    }
    return params;
}

//初始化select集合数据
function initSelListByUrl(url,id,key,value){
    $.post(url,function(dataList){
        initSelListByListData(dataList,id,key,value,false);
    },'json');
}
//初始化select集合数据
function initAllSelListByUrl(url,id,key,value){
    $.post(url,function(dataList){
        initSelListByListData(dataList,id,key,value,true);
    },'json');
}

/**
 *
 * @param dataList 数据集
 * @param id  select id
 * @param key 数据项 key
 * @param value 数据项 value
 * @param isAll 是否初始化所有的select id
 */
function initSelListByListData(dataList,id,key,value,isAll){
    if(isAll){
        $(document).find("select[id="+id+"]").each(function(){
            initSingleSelListByListData(dataList,$(this),key,value);
        });
    }else{
        initSingleSelListByListData(dataList,$("#"+id),key,value);
    }

}
function initSingleSelListByListData(dataList,idObj,key,value){
    var dbId = idObj.attr("param-id");
    var strArr = new Array();
    for(var index in dataList){
        var data = dataList[index];
        strArr.push("<option value='");
        var keyVal = data[''+key+''];
        strArr.push(keyVal);
        strArr.push("'");
        if(dbId && dbId == keyVal){
            strArr.push("selected='selected'");
        }
        strArr.push(">");
        strArr.push(data[''+value+'']);
        strArr.push("</option>");
    }
    idObj.append(strArr.join(""));
}


