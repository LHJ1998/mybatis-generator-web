layui.use(['form'], function(){
	var form = layui.form;

	form.on('submit(link)', function(data){
		$.ajax({
			url: 'http://localhost:8080/db/link',
			type: 'POST',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data.field),
			dataType: 'JSON',
			success: function(res){
				if(res.code === 200){
					layer.alert('数据库连接成功');
					$('#db-table').css('display', 'block');
					var tables = res.data;
					for(var i = 0; i < tables.length; i++){
						$('#tables').append('<input type="checkbox" name="table" value="' + tables[i] + '" title="' + tables[i] + '">')
					}
					form.render();
				} else{
					layer.alert('数据库连接失败');
				}
			},
			error: function(res){
				layer.alert('数据库连接失败');
			}
		});
		return false;
	});

	form.on('submit(generate)', function(data){
		var tables = [];
		$("input:checkbox[name='table']:checked").each(function(i){
			tables[i] = $(this).val();
		});
		data.field.table = tables;

		$.ajax({
			url: 'http://localhost:8080/db/generate',
			type: 'POST',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data.field),
			dataType: 'JSON',
			success: function(res){
				if(res.code === 200){
					layer.alert('文件生成成功');
				} else{
					layer.alert('文件生成成功');
				}
			},
			error: function(res){
				layer.alert('文件生成失败');
			}
		});
		return false;
	});
});