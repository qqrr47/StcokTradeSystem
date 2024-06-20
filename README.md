1、	系统支持两种角色：游客和登陆用户，不同角色的功能不同。
2、	对于游客，可以使用的功能包括：注册、登陆、浏览大盘行情、浏览个股实时行情。
 

其中：
a)	游客注册时需要输入用户名、密码、确认密码，在页面上使用JS检查后两者是否一致，一致时将信息返回后端，完成注册。注册后用户默认的账户与、额是10000元。
b)	游客登陆时需要输入用户名、密码，页面将信息返回后端进行比较，并根据结果提示登陆失败或跳转到已登陆页面。
c)	浏览大盘行情时，根据后端返回的股票代码，将所有股票分为三部分展示（可自由切换）：沪市（6开头）、深市（3开头）、创业板（0开头）。所有股票的信息以表格的形式展示，每行展示一支股票，展示的信息包括：代码、名称、最新价格、当日涨跌幅、当日涨跌价。页面需要定时（显示倒计时）向后端获取数据，以刷新页面中的股票数据。
d)	选择具体股票后，可以浏览以曲线的形式展示的股票实时行情。页面需要定时（显示倒计时）向后端获取数据，以刷新页面中的股票数据。
3、	对于登陆用户，可以使用的功能（图2）包括：注销、浏览大盘行情、浏览个股实时行情、交易股票、查看持仓、查看交易记录。
 
登陆用户功能
其中：
a)	登陆用户注销后，将返回游客页面。
b)	浏览大盘行情和浏览个股实时行情功能与游客相应功能一致。
c)	登陆用户可以选择某支股票，输入交易类型、数量和价格进行交易。并根据后端返回的数据，在页面显示委托成功、交易成功、废单等提示。
d)	登陆用户可以查看自己的持仓，页面以表格的形式展示当前用户的账户余额以及所有已买入且未卖出的股票信息，包括：代码、名称、买入价格、最新价格、盈亏金额。页面需要定时（显示倒计时）向后端获取数据，以刷新页面中的股票数据。
e)	登陆用户可以查看自己的交易记录，页面以表格的形式展示当前用户的账户余额以及所有买入、卖出的记录，包括：交易时间、代码、名称、交易方向、金额、数量。