$(document).ready(function() {
	$('.form-link').each(function() {
		var $link = $(this);
		var $dialog = $('<div></div>')
			.load($link.attr('href') + ' .form-content')
			.dialog({
				autoOpen: false,
				title: $link.attr('title')
			});

		$link.click(function() {
			$dialog.dialog('open');

			return false;
		});
	});
});