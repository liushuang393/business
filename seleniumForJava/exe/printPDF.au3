#region --- Au3Recorder generated code Start (v3.3.9.5 KeyboardLayout=00000411)  ---

#region --- Internal functions Au3Recorder Start ---
Func _Au3RecordSetup()
Opt('WinWaitDelay',100)
Opt('WinDetectHiddenText',1)
Opt('MouseCoordMode',0)
#Local $aResult = DllCall('User32.dll', 'int', 'GetKeyboardLayoutNameW', 'wstr', '')
#If $aResult[1] <> '00000411' Then
#  MsgBox(64, 'Warning', 'Recording has been done under a different Keyboard layout' & @CRLF & '(00000411->' & $aResult[1] & ')')
#EndIf

EndFunc

Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
EndFunc

_AU3RecordSetup()
#endregion --- Internal functions Au3Recorder End ---

;ControlFocus ( "title", "窗口文本", controlID)   设置输入焦点到指定窗口的某个控件上
;WinWait ( "title题" , "窗口文本" , 超时时间 )  暂停脚本的执行直至指定窗口存在（出现）为止
;ControlSetText ( "title", "窗口文本", controlID, "新文本" )   修改指定控件的文本
;Sleep ( 延迟 )   使脚本暂停指定时间段
;ControlClick ( "title", "窗口文本", 控件ID , 按钮 , 点击次数 )   向指定控件发送鼠标点击命令
;其中，title即AutoIt Window Info识别出的Title字段，controlID即AutoIt Window Info识别
;出的Class和Instance的拼接，如上图拼接后的结果应为：Button1
;ControlClick(
;ControlClick("另存为","保存","Button2")

_WinWaitActivate("名前を付けて保存","Namespace Tree Contr")
ControlFocus("名前を付けて保存","text","1001")
WinWait("[CLASS:;32770]","",100)
ControlSetText("名前を付けて保存","","[CLASS:Edit; INSTANCE:1]",$CmdLine[1])
Sleep(1000)
ControlClick("名前を付けて保存","保存","Button2")



