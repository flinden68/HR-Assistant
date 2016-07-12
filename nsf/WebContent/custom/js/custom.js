/**
 * Module for displaying "Waiting for..." dialog using Bootstrap
 * 
 * @author Eugene Maslovich <ehpc@em42.ru>
 */

var waitingDialog = waitingDialog || ( function($) {
	'use strict';

	// Creating modal dialog's DOM
		var $dialog = $('<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">'
				+ '<div class="modal-dialog modal-m">'
				+ '<div class="modal-content">'
				+ '<div class="modal-header"><h3 style="margin:0;"></h3></div>'
				+ '<div class="modal-body">'
				+ '<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>'
				+ '</div>' + '</div></div></div>');

		return {
			/**
			 * Opens our dialog
			 * 
			 * @param message
			 *            Custom message
			 * @param options
			 *            Custom options: options.dialogSize - bootstrap postfix
			 *            for dialog size, e.g. "sm", "m"; options.progressType -
			 *            bootstrap postfix for progress bar type, e.g.
			 *            "success", "warning".
			 */
			show : function(message, options) {
				// Assigning defaults
			if (typeof options === 'undefined') {
				options = {};
			}
			if (typeof message === 'undefined') {
				message = 'Loading';
			}
			var settings = $.extend( {
				dialogSize : 'm',
				progressType : '',
				onHide : null
			// This callback runs after the dialog was hidden
					}, options);

			// Configuring dialog
			$dialog.find('.modal-dialog').attr('class', 'modal-dialog')
					.addClass('modal-' + settings.dialogSize);
			$dialog.find('.progress-bar').attr('class', 'progress-bar');
			if (settings.progressType) {
				$dialog.find('.progress-bar').addClass(
						'progress-bar-' + settings.progressType);
			}
			$dialog.find('h3').text(message);
			// Adding callbacks
			if (typeof settings.onHide === 'function') {
				$dialog.off('hidden.bs.modal').on('hidden.bs.modal',
						function(e) {
							settings.onHide.call($dialog);
						});
			}
			// Opening dialog
			$dialog.modal();
		},
		/**
		 * Closes dialog
		 */
		hide : function() {
			$dialog.modal('hide');
		}
		};

	})(jQuery);

function onKeyPressSubmit(buttonId, thisEvent) {
	if (typeof thisEvent == 'undefined' && window.event) {
		thisEvent = window.event;
	}
	if (thisEvent.keyCode == dojo.keys.ENTER) {
		dojo.byId(buttonId).click();
		thisEvent.preventDefault();
	}
}

function openUrl(targetUrl) {
	if (targetUrl != "" && targetUrl != null) {
		document.location = targetUrl;
	}
}

$(document).on('change', '.btn-file :file', function() {
	  var input = $(this),
	      numFiles = input.get(0).files ? input.get(0).files.length : 1,
	      label = input.val().replace(/\\/g, '/').replace(/.*\//, ''); 
	  //input.trigger('fileselect', [numFiles, label]);
	      var input = $(this).parents('.input-group').find(':text'),
        log = numFiles > 1 ? numFiles + ' files selected' : label;
    if( input.length ) {
        input.val(log);
    } else {
        if( log ) alert(log);
    }
});


$(document).ready( function() {
	    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
	        var input = $(this).parents('.input-group').find(':text'),
	            log = numFiles > 1 ? numFiles + ' files selected' : label;
	        if( input.length ) {
	            input.val(log);
	        } else {
	            if( log ) alert(log);
	        }
	        
	    });
});

function x$(idTag, param){ //Updated 18 Feb 2012
	   idTag=idTag.replace(/:/gi, "\\:")+(param ? param : "");
	   return($("#"+idTag));
	}