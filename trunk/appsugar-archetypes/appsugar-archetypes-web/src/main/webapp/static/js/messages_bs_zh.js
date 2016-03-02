/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必填字段",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});

jQuery.extend(jQuery.validator.defaults, {
    errorElement: "span", 
    errorClass:"help-inline",
    highlight : function (e) {
		$(e).removeClass('info').addClass('error');
	},
	unhighlight : function (e) {
		$(e).removeClass('error');
	},
	errorPlacement: function (error, element) {
		if($(element).is("select")){
			error.appendTo($(element).parent());
		}else{
			error.insertAfter(element);
		}
	}
});

//手机验证
jQuery.validator.addMethod("isMobile", function(value, element) {       
   	var length = value.length;   
   	var mobile =  /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;   
   	return this.optional(element) || (length == 11 && mobile.test(value));       
}, "请输入正确的手机号");

//身份证验证
jQuery.validator.addMethod("isCarCode", function(value, element) {   
    value = value.toUpperCase();
	//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。  
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(value))){
		//alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
		return this.optional(element) || false;
	}
	//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	//下面分别分析出生日期和校验位

	var len, re;
	len = value.length;
   if (len == 15){
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = value.match(re);
		//检查生日日期是否正确
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			//alert('输入的身份证号里出生日期不对！');  
			return this.optional(element) || false;
		}else{
		 //将15位身份证转成18位
		 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
			  var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			  var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
			  var nTemp = 0, i;  
			  value = value.substr(0, 6) + '19' + value.substr(6, value.length - 6);
			  for(i = 0; i < 17; i ++){
					nTemp += value.substr(i, 1) * arrInt[i];
			  }
			   value += arrCh[nTemp % 11];  
			   return this.optional(element) || true;
		}  

   }
   if (len == 18){
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		var arrSplit = value.match(re);
		//检查生日日期是否正确
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			//alert(dtmBirth.getYear());
			//alert(arrSplit[2]);
			//alert('输入的身份证号里出生日期不对！');
			return this.optional(element) || false;
	   }else{

			//检验18位身份证的校验码是否正确。
			//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
			var valnum;
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
			var nTemp = 0, i;
			for(i = 0; i < 17; i ++){
				nTemp += value.substr(i, 1) * arrInt[i];
			}
			valnum = arrCh[nTemp % 11];
			//alert(value+"  "+ valnum);
			
			if (valnum != value.substr(17, 1)){
			//alert('18位身份证的校验码不正确！应该为：' + valnum);
			return this.optional(element) || false;
			}
			return this.optional(element) || true;
		}
	}		
	}, "请正确填写身份证号码"); 