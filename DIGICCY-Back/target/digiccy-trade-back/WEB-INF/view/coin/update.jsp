<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>上传文件</title>
 
    </head>
    <body>
        <div class="container">
            <form action="/coin/update.do" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td><label for="file">上传文件：</label></td>
                        <td><input type="file" id="file" name="picture" value=""/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="提交"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>