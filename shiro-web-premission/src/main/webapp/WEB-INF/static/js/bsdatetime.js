
		/*在初始化的时候，使用defaultDate指定默认时间 */
		/* $('#date1').datetimepicker({
			format : 'YYYY-MM-DD hh:mm:ss',
			locale : moment.locale('zh-cn'),
			defaultDate : "2017-01-01"
		}); */
		/*  */
		$(function() {
			var picker1 = $('#datetimepicker1').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment.locale('zh-cn'),
				//设置最小日期
				//minDate: '2016-7-1'  
			});
			var picker2 = $('#datetimepicker2').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment.locale('zh-cn')
			});
			//动态设置最小值  
			picker1.on('dp.change', function(e) {
				picker2.data('DateTimePicker').minDate(e.date);
			});
			//动态设置最大值  
			picker2.on('dp.change', function(e) {
				picker1.data('DateTimePicker').maxDate(e.date);
			});
			
			//---------------------------------------------------//
			var picker3 = $('#datetimepicker3').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment.locale('zh-cn'),
				//设置最小日期
				//minDate: '2016-7-1'  
			});
			var picker4 = $('#datetimepicker4').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment.locale('zh-cn')
			});
			//动态设置最小值  
			picker3.on('dp.change', function(e) {
				picker4.data('DateTimePicker').minDate(e.date);
			});
			//动态设置最大值  
			picker4.on('dp.change', function(e) {
				picker3.data('DateTimePicker').maxDate(e.date);
			});
			
			//---------------------------------------------------//
			var picker5 = $('#datetimepicker5').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment.locale('zh-cn'),
				//设置最小日期
				//minDate: '2016-7-1'  
			});
			var picker6 = $('#datetimepicker6').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment.locale('zh-cn')
			});
			//动态设置最小值  
			picker5.on('dp.change', function(e) {
				picker6.data('DateTimePicker').minDate(e.date);
			});
			//动态设置最大值  
			picker6.on('dp.change', function(e) {
				picker5.data('DateTimePicker').maxDate(e.date);
			});
			
		});
