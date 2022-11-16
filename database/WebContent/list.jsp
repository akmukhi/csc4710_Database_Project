<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE>
    <html>
        <head>
            <meta charset = "ISO-8859-1">
            <title>Listing an NFT</title>
        </head>
        <body>
            <div class = "listBlock">
                <form action="Listactivenft" methods ="posts">
                <table border="1" cellpadding="5">
				<tr>
					<th>NFT Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="nftName" size="45"  value="nftName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
                    <td align="center" colspan="5">
						<input type="submit" value="listactivenft"/>
					</td>
                   </tr>
                 </table>
                </form>
            </div>
        </body>
    </html>
