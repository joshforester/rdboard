﻿/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
config.toolbar = 'Full';

config.toolbar_Full =
[
 ['Source','-','Templates'],
 ['Cut','Copy','Paste','PasteText','PasteFromWord','-', 'SpellChecker', 'Scayt'],
 ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
 '/',
 ['Bold','Italic','Underline','Strike'],
 ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
 ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
 ['Link','Unlink'],
 ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
 '/',
 ['Styles','Format','Font','FontSize'],
 ['TextColor','BGColor'],
 ['Maximize', 'ShowBlocks']
];

};
