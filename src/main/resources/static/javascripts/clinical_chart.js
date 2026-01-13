/**
 * 临床试验统计数据显示脚本
 */

$(document).ready(function() {
	// 从后端获取的数据（通过 Thymeleaf 注入）
	const totalTrials = parseInt($('#totalTrials').data('value') || 0);
	const activeTrials = parseInt($('#recruitingTrials').data('value') || 0);
	const totalSubjects = parseInt($('#totalSubjects').data('value') || 0);
	const successfulMatches = parseInt($('#successfulMatches').data('value') || 0);
	
	// 显示统计数据
	$('#totalTrials').text(totalTrials);
	$('#recruitingTrials').text(activeTrials);
	$('#totalSubjects').text(totalSubjects);
	$('#successfulMatches').text(successfulMatches);
});
