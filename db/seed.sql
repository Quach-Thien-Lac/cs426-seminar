USE Sanguosuo;
SET NAMES UTF8MB4 COLLATE UTF8MB4_UNICODE_CI;

INSERT INTO UserStatus (user_status_code, user_status_name) VALUES
	('ACTIVE', 'Active'),
	('INACTIVE', 'Inactive'),
	('DISABLED', 'Disabled');

INSERT INTO UserRole (user_role_code, user_role_name) VALUES
	('VIEWER', 'Viewer'),
	('EDITOR', 'Editor'),
	('ADMIN', 'Administrator');

INSERT INTO SkillTag (skill_tag_code, skill_tag_name) VALUES
	('CTK', 'Chủ Tướng Kỹ'),
	('PTK', 'Phó Tướng Kỹ'),
	('TDK', 'Toả Định Kỹ'),
	('HDK', 'Hạn Định Kỹ'),
	('TPK', 'Trận Pháp Kỹ'),
	('GM', 'Giảm 0.5 Máu'),
	('CHK', 'Chuyển Hoán Kỹ');

INSERT INTO HeroFaction (hero_faction_code, hero_faction_name) VALUES
	('WEI', 'Nguỵ'),
	('SHU', 'Thục'),
	('WU', 'Ngô'),
	('QUN', 'Quần');

INSERT INTO HeroSkill (skill_id, skill_name, skill_description) VALUES
	('WEI001_1','Gian Hùng','Sau khi bạn nhận sát thương, bạn có thể thu lấy thẻ bài gây sát thương cho bạn lên tay hoặc chọn rút 1 lá bài.')
	,('WEI002_1','Hành Thương','Khi có 1 người chơi khác trận vong, bạn có thể thu lấy tất cả thẻ bài mà người chơi đó sở hữu.')
	,('WEI002_2','Phóng Trục','Sau khi bạn nhận sát thương, bạn có thể lệnh cho 1 người chơi khác đặt chồng tướng/giải chồng tướng, sau đó họ rút X thẻ bài (X là số sinh lực bạn đã mất).')
	,('WEI003_1','Phản Quỷ','Sau khi bạn nhận sát thương, bạn có thể thu lấy 1 thẻ bài của nguồn gây ra sát thương.')
	,('WEI003_2','Quỷ Tài','Trước khi thẻ phán xét của 1 người chơi có hiệu lực bạn có thể đánh ra 1 thẻ bài để thay thế thẻ phán xét đó.')
	,('WEI004_1','Thiên Đố','Sau khi thẻ phán xét của bạn có hiệu lực, bạn có thể thu lấy thẻ phán xét này lên tay.')
	,('WEI004_2','Di Kế','Sau khi bạn nhận sát thương, bạn có thể xem 2 thẻ từ đầu chồng bài, mỗi thẻ có thể giao cho 1 người chơi tùy ý.')
	,('WEI005_1','Khỏa Y','Kết thúc giai đoạn rút bài, bạn có thể bỏ đi 1 thẻ bài. Nếu làm như thế, cho đến khi hết lượt, bạn sử dụng [Sát] hoặc [Quyết Đấu] lên mục tiêu tạo thành sát thương, thì sát thương bạn gây ra cho họ +1.')
	,('WEI006_1','Cường Tập','Giai đoạn hành động giới hạn 1 lần, bạn có thể chọn tự mất 1 sinh lực hoặc bỏ đi 1 thẻ bài Vũ Khí, sau đó bạn chọn 1 người khác nằm trong phạm vi công kích của bạn, bạn gây ra 1 sát thương đối với người chơi đó.')
	,('WEI007_1','Cương Liệt','Sau khi nhận sát thương, bạn có thể tiến hành phán xét. Nếu thẻ phán xét không phải chất ♥️, bạn lệnh nguồn sát thương lựa chọn 1 mục:\n\t1. Bỏ đi 2 thẻ trên tay.\n\t2. Nhận 1 điểm sát thương từ bạn.')
	,('WEI008_1','Thần Tốc','Bạn có thể lựa chọn tối đa 3 mục:\n\t1. Bỏ qua giai đoạn phán xét và rút bài.\n\t2. Bỏ qua giai đoạn hành động và bỏ đi 1 thẻ Trang Bị.\n\t3. Bỏ qua giai đoạn bỏ bài và tự giảm 1 sinh lực.\nVới mỗi 1 mục bạn lựa chọn, bạn xem như sử dụng 1 thẻ [Sát] phổ thông không giới hạn khoảng cách đối với 1 người chơi khác.')
	,('WEI009_1','Đột Tập','Giai đoạn rút bài, bạn có thể rút ít đi số lượng thẻ bài tùy ý, rồi thu lấy 1 thẻ bài trên tay của số lượng người chơi khác bằng số lượng thẻ bài bạn rút ít đi.')
	,('WEI010_1','Xảo Biến','Bạn có thể bỏ 1 thẻ trên tay để bỏ qua 1 giai đoạn trong lượt này của bạn (trừ chuẩn bị và kết thúc). Nếu theo cách này bỏ qua giai đoạn rút bài, bạn có thể thu lấy 1 thẻ trên tay của tối đa 2 người chơi khác; Bỏ qua giai đoạn hành động, bạn có thể di chuyển 1 thẻ bài trên bàn chơi.')
	,('WEI011_1','Khu Hổ','Một lần trong giai đoạn hành động, bạn có thể đấu điểm với 1 người chơi có sinh lực > bạn. Nếu bạn thắng, bạn lệnh họ gây 1 sát thương cho 1 người chơi trong phạm vi công kích của họ do bạn chỉ định. Nếu bạn không thắng, họ gây cho bạn 1 sát thương.')
	,('WEI011_2','Tiết Mệnh','Sau khi bạn nhận sát thương, bạn có thể lệnh cho 1 người chơi bổ sung bài trên tay bằng với giới hạn sinh lực của họ (tối đa 5 thẻ).')
	,('WEI012_1','Kiêu Quả','Giai đoạn kết thúc của người chơi khác, bạn có thể bỏ đi 1 thẻ Cơ Bản đồng thời lệnh họ chọn 1 mục:\n\t1. Họ tự bỏ đi 1 thẻ Trang Bị, sau đó bạn rút 1 thẻ.\n\t2. Họ nhận 1 điểm sát thương từ bạn.')
	,('WEI013_1','Khuynh Quốc','Bạn có thể sử dụng hoặc đánh ra thẻ bài sắc Đen trên tay xem như thẻ [Né].')
	,('WEI013_2','Lạc Thần','Giai đoạn chuẩn bị, bạn có thể tiến hành phán xét, nếu kết quả là sắc Đen, bạn có thể lần nữa tiến hành phán xét cho đến khi kết quả phán xét là sắc Đỏ thì dừng lại. Sau đó bạn thu lấy những thẻ phán xét sắc Đen của bạn lên tay.')
	,('WEI014_1','Cự Thủ','Giai đoạn kết thúc, bạn có thể rút X thẻ bài (X là số thế lực trên bàn chơi), sau đó bỏ đi 1 thẻ bài trên tay, nếu theo cách này thẻ bỏ đi là thẻ Trang Bị, đổi lại thành bạn vừa sử dụng thẻ đó. Nếu X > 2, bạn đặt chồng thẻ võ tướng.')
	,('WEI015_1','Đoạn Lương','Giai đoạn hành động, bạn có thể sử dụng thẻ bài Phi Cẩm Nang sắc Đen xem như 1 thẻ [Binh Lương Thốn Đoạn] không hạn chế khoảng cách. Nếu bạn vừa sử dụng thẻ [Binh Lương Thốn Đoạn] đối với 1 người chới trong khoảng cách vượt quá 2, bạn không thể tái phát động kỹ năng cho đến hết lượt.')
	,('WEI016_1','Ngụy Thành','Khi bạn giao bài trên tay cho người khác, hoặc sau khi thẻ bài của bạn bị người khác thu lấy. Nếu bài trên tay của bạn < sinh lực hiện tai, bạn có thể rút 1 thẻ.')
	,('WEI016_2','Đạo Thư','Giai đoạn hành động 1 lần, bạn có thể gọi tên 1 chất, rồi thu lấy 1 thẻ trên tay 1 người khác đồng thời lật mở ra. Nếu đồng chất với lựa chọn của bạn, bạn gây 1 sát thương lên họ và kỹ năng này xem như chưa phát động. Nếu khác chất, bạn chọn lật mở 1 thẻ trên tay khác chất với thẻ bạn thu được, sau đó giao lại cho họ.')
	,('WEI017_1','Truân Điền','Sau khi bạn mất đi thẻ bài ngoài lượt, bạn có thể tiến hành phán xét, nếu kết quả phán xét không phải chât ♥️, bạn có thể đặt thẻ phán xét này lên tướng của bạn gọi là thẻ "Điền"; Với mỗi thẻ "Điền", bạn tính toán khoảng cách đến người khác -1.')
	,('WEI017_2','Cấp Tập','Giai đoạn hành động, bạn dùng thẻ "Điền" xem như thẻ [Thuận Thủ Khiên Dương].')
	,('WEI017_3','Tư Lương','Sau khi 1 người cùng thế lực với bạn nhận sát thương, bạn có thể chọn giao cho họ 1 thẻ "Điền".')
	,('WEI018_1','Hộ Viện','Trong lượt của bạn, sau khi có thẻ tiến vào vùng Trang Bị, bạn có thể bỏ 1 thẻ của người chơi khác nằm trong khoảng cách 1 của người được lắp. Giai đoạn kết thúc, bạn có thể đem 1 trang bị lắp vào vùng trống ở vùng trang bị của 1 người chơi.')
	,('WEI018_2','Hạc Dực','Người ở cùng đội hình với bạn đều xem như nhận kỹ năng『Phi Ảnh』.')
	,('WEI018_3','Phi Ảnh','Người chơi khác tính toán khoảng cách đến bạn +1.')
	,('WEI019_1','Tuân Tuân','Giai đoạn rút bài, bạn có thể xem trước 4 thẻ bài từ đầu chồng bài. Sau đó đem 2 thẻ bài trong số đó đặt lên trên chồng bài rút, còn lại xếp trình tự tùy ý đặt xuống dưới đáy chồng bài rút.')
	,('WEI019_2','Vong Kích','Sau khi bạn gây ra 1 sát thương cho 1 người khác, hoặc bạn nhận 1 sát thương từ người chơi khác, bạn có thể cùng họ rút 1 thẻ.')
	,('WEI020_1','Hoành Giang','Sau khi bạn nhận 1 sát thương, bạn có thể lệnh giới hạn trữ bài trên tay của người có lượt hiện tại -X ở lượt này (X là thẻ trong vùng trang bị của họ, tối thiểu là 1). Sau đó khi lượt này kết thúc, nếu họ trong giai đoạn bỏ bài lượt này không có bỏ bài, bạn bổ sung bài trên tay đến giới hạn sinh lực.')
	,('WEI021_1','Kỳ Sách','Một lần trong giai đoạn hành động, bạn có thể sử dụng tất cả thẻ bài trên tay xem như 1 thẻ Cẩm Nang phi thời gian (số mục tiêu được chọn không được vượt quá số thẻ bài trên tay bạn đã dùng theo cách này), sau đó bạn có thể đổi Phó Tướng (1 lần).')
	,('WEI021_2','Trí Ngu','Sau khi bạn nhận sát thương, bạn có thể rút 1 thẻ và mở ra toàn bộ bài trên tay, nếu tất cả đều cùng 1 màu với nhau, bạn lệnh nguồn sát thương bỏ 1 thẻ bài trên tay.')
	,('WEI022_1','Văn Ngụy','Khi thẻ bài của bạn trở thành mục tiêu do người khác chỉ định thu lấy hoặc bỏ đi, bạn có thể sửa thành chính bạn tự chọn thẻ bài bị mất đi, sau đó bạn rút 1 thẻ.')
	,('WEI022_2','Ước Kiệm','Bắt đầu giai đoạn bỏ bài của người cùng thế lực, nếu ở lượt này họ không sử dụng bài chỉ định người khác làm mục tiêu, thì giới hạn trữ bài ở lượt này bằng giới hạn sinh lực của người đó.')
	,('WEI023_1','Chính Tịch','Bắt đầu giai đoạn hành động, bạn có thể lựa chọn 1 mục:\n\t1. Chọn 1 người chơi khác Vô thế lực, cho đến khi kết thúc giai đoạn này, nếu họ xác định thế lực, bạn thu 1 thẻ trên tay và 1 thẻ trong vùng trang bị của họ.\n\t2. Chọn 1 người chơi khác Có thế lực, bạn giao cho họ 1 thẻ Cơ Bản, họ chọn giao lại cho bạn 1 thẻ Phi Cơ Bản hoặc 2 thẻ Cơ Bản.')
	,('WEI023_2','Phượng Nghênh','Bạn có thể đem tất cả bài trên tay sử dụng như thẻ [Hiệp Thiên Tử Dĩ Lệnh Chư Hầu] (bỏ qua điều kiện Đại Thế Lực), sau đó những người cùng thế lực bổ sung bài trên tay đến giới hạn sinh lực.')
	,('WEI024_1','Tiết Việt','Giai đoạn chuẩn bị, bạn có thể giao cho 1 người chơi không là thế lực Ngụy 1 thẻ bài trên tay, sau đó bạn lệnh họ thực hiện 1 [Quân Lệnh]. Nếu họ chấp hành, bạn rút 1 thẻ bài. Nếu không, bạn rút thêm 3 thẻ trong giai đoạn rút bài tại lượt này.')
	,('WEI025_1','Vong Quy','Mỗi lượt 1 lần, sau khi bạn gây/nhận sát thương, nếu: Bạn chỉ đang lật mở tướng này, bạn có thể gây 1 sát thương cho người không cùng thế lực với bạn; Bạn đã mở tất cả tướng, bạn có thể lệnh những người cùng thế lực rút 1 thẻ.')
	,('WEI025_2','Tức Binh','Sau khi người khác trong giai đoạn hành động sử dụng thẻ [Sát] sắc Đen đầu tiên hoặc cẩm nang phổ thông sắc Đen đầu tiên chỉ định 1 mục tiêu duy nhất, nếu bài trên tay họ < sinh lực của họ, bạn có thể lệnh họ bổ sung bài trên tay đến giới hạn sinh lực và họ không thể tiếp tục sử dụng bài trên tay trong lượt này. Nếu bạn và họ đều lật mở tất cả tướng, bạn có thể lật úp 1 tướng của bạn và họ, sau đó lượt này không thể lật mở lại thẻ tướng đó.')
	,('WEI026_1','Đức Thiệu','Mỗi lượt tối đa X lần (X là số sinh lực hiện tại của bạn), sau khi bạn trở thành mục tiêu thẻ bài sắc Đen của người khác, nếu số tướng lật mở của họ ≤ bạn, bạn bỏ đi 1 thẻ bài của họ.')
	,('WEI026_2','Minh Phạt','Một lần trong giai đoạn hành động, bạn có thể lựa chọn 1 người khác không cùng thế lực, khi họ kết thúc lượt tiếp theo của họ:\n\t- Nếu bài trên tay họ < bạn, bạn gây 1 sát thương cho họ, đồng thời thu lấy 1 thẻ trên tay của họ.\n\t- Nếu bài trên tay họ ≥ bạn, bạn bổ sung bài trên tay bằng với bài trên tay họ (tối đa 5 thẻ).')
	,('WEI027_1','Chấn Tập','Sau khi bạn sát định mục tiêu thẻ [Sát], ứng với mỗi mục tiêu, bạn chọn 1 mục\n\t1. Bạn bỏ 1 thẻ của họ.\n\t2. Bạn sử dụng chuyển hoá 1 thẻ phi cẩm nang chất ♣️/♦️ thành [Binh Lương Thốn Đoạn]/[Lạc Bất Tư Thục] không giới hạn khoảng cách với họ.\nNếu bạn mở 2 tướng và họ có tướng úp, bạn có thể chấp hành 2 mục với thứ tự tuỳ ý.')
	,('WEI027_2','Kiệm Tố','Khi bạn nhận bài ngoài lượt, bạn có thể gọi những thẻ bài trên tay này là "Kim" chừng nào thẻ này còn trên tay bạn. "Kim" luôn công khai với những người khác. Bắt đầu giai đoạn hành động, bạn có thể bỏ đi tuỳ ý "Kim", sau đó chọn 1 người đã bị thương có số màu <= số "Kim" đã bỏ, lệnh họ hồi 1 sinh lực.')
	,('WEI028_1','Thượng Vũ','Bạn có thể đem tuỳ ý số thẻ Cẩm Nang xem như [Sát]/[Né] để sử dụng hoặc đánh ra. Nếu bằng cách này sát thương bạn gây ra +X (X là số thẻ được sử dụng để chuyển hoá -1, tối đa bằng giá trị sinh lực của người chơi đang có lượt -1).')
	,('WEI028_2','Dịch Mã','Giai đoạn hành động giới hạn 1 lần, bạn có thể giao cho 1 người khác có trang bị Toạ Kỵ 1 thẻ [Đào]/[Tửu], sau đó bạn thu lấy 1 thẻ Toạ Kỵ trong vùng trang bị của họ. Nếu sinh lực của bạn hiện có là 1, bạn có thể sửa thành giao 1 thẻ Cơ Bản.')
	,('WEI029_1','Thiết Phục','Giai đoạn hành động 1 lần, bạn có thể đặt 1 thẻ trên tay xuống tướng này, gọi là "Phục Binh". Khi 1 người khác sử dụng 1 thẻ trên tay, bạn có thể di dời "Phục Binh" cùng tên, sau đó huỷ bỏ tất cả mục tiêu của thẻ này. Bắt đầu lượt của bạn, nếu số "Phục Binh" > 2, bạn bỏ đi còn 2.')
	,('WEI029_2','Bí Dục','Sau khi bạn nhận sát thương:\n\t- Nếu bài trên tay bạn < nguồn sát thương, bạn có thể chọn 1 mục\n\t\t1. Bạn bổ sung bài trên tay đến khi bằng họ (tối đa 5 thẻ).\n\t\t2. Lệnh họ bỏ đi bài trên tay đến khi bằng bạn (tối đa 5 thẻ).\n\t- Nếu bài trên tay bạn > nguồn sát thương, bạn có thể bỏ số bài trên tay bằng số bài trên tay họ +1, sau đó gây 1 sát thương cho họ.')
	,('WEI030_1','Tinh Sách','Giai đoạn hành động, sau khi bạn sử dụng thẻ thứ X kết toán xong (X là số sinh lực hiện tại của bạn) và không có người đang trong trạng thái hấp hối, bạn có thể lệnh 1 người khác thế lực chấp hành 1 [Quân Lệnh], nếu họ không chấp hành, bạn rút 2 thẻ bài.')
	,('WEI031_1','Lâm Lang','Sau khi thẻ phán xét có hiệu lực, nếu thẻ đó là Cẩm Nang, bạn có thể lựa chọn:\n\t1. Sau khi thẻ này tiến vào chồng bài bỏ, bạn thu lên tay.\n\t2. Di chuyển 1 thẻ trên bàn chơi có cùng màu với thẻ phán xét đó.')
	,('WEI031_2','Lạc Anh','Sau khi bạn giải chồng tướng, bạn có thể tiến hành phán xét. Nếu thẻ đó chất ♣️, khi kết thúc lượt hiện tại bạn nhận ngay 1 lượt chơi chỉ có giai đoạn hành động. Sau khi bạn nhận sát thương, bạn có thể rút X thẻ đồng thời thay đổi trạng thái chồng tướng (X là sinh lực đã mất của bạn).')
	,('WEI032_1','Y Tú','Sau khi bạn nhận được thẻ bài, bạn có thể lật mở thẻ đó đồng thời có thể sử dụng bất kỳ số lượng thẻ trang bị nào trong đó, sau đó rút thêm bài bằng số thẻ đã dùng.')
	,('WEI032_2','Nhã Thương','Sau khi bạn nhận sát thương, nếu nguồn sát thương:\n\t1. Khác thế lực, họ phải bỏ bài trên tay cho đến khi còn X thẻ.\n\t2. Cùng thế lực, bạn phải bỏ bài trên tay cho đến khi còn X thẻ.\nNếu bằng cách này không có ai bỏ bài, bên còn lại bổ sung bài trên tay đến X (X là số vùng trang bị trống của bạn).')
	,('WEI033_1','Hiếu Liêm','Khi bạn lật mở thẻ tướng này, bạn có thể di chuyển tối đa 2 thẻ Trang Bị trên bàn chơi. Khi bạn di trừ thẻ tướng này, những người cùng thế lực với bạn rút 1 thẻ.')
	,('WEI033_2','Khẳng Khái','Sau khi 1 người cùng thế lực với bạn thành mục tiêu của [Sát], bạn có thể di trừ tướng này, thực hiện lần lượt:\n\t- Chọn 1 người, lệnh họ nhận kỹ năng 『Phi Ảnh』\n\t- Nếu người đó không phải bạn, người đó hồi 1 sinh lực và thoát trạng thái liên hoàn.')
	,('WEI034_1','Quả Quyết','Sau khi bạn mở thẻ tướng này lần đầu tiên, bạn gây 1 sát thương cho 1 người khác; Khi 1 người chơi rơi vào trạng thái hấp hối bởi bạn, bạn có thể bỏ 1 thẻ của họ.')
	,('WEI034_2','Thương Thệ','Sau khi bạn nhận sát thương, bạn có thể chọn bỏ 1 thẻ hoặc giao X thẻ trên tay cho 1 người khác; Nếu làm vậy, bạn rút X thẻ (X là số sinh lực bạn đã mất).')
	,('WEI035_1','Trinh Liệt','Khi bạn trở thành mục tiêu của thẻ [Sát] hoặc Cẩm Nang phổ thông do người khác sử dụng, bạn có thể tự giảm 1 sinh lực, huỷ bỏ nó, sau đó bạn bỏ 1 thẻ bài của họ.')
	,('WEI035_2','Bí Kế','Mỗi vòng giới hạn 1 lần, giai đoạn chuẩn bị của người khác, nếu bạn đang bị thương và họ không có [Quân Lệnh], bạn có thể tiến hành »Hiến Sách« đối với họ. Nếu làm như vậy, trong giai đoạn hành động, họ lựa chọn 1 mục:\n\t1. Họ lệnh 1 người ngoài họ và ngoài bạn chấp hành [Quân Lệnh] này.\n\t2. Họ bỏ đi [Quân Lệnh] này, »Kiểm Sách« và thu lấy 1 thẻ cẩm nang.')
	,('WEI036_1','Tuyệt Tình','Khi bạn gây sát thương cho người chơi khác, bạn có thể chặn sát thương này lại, và lệnh cho mục tiêu tự giảm lượng sinh lực tương đương.')
	,('WEI036_2','Thương Thệ','Khi có người chơi tự giảm sinh lực, với mỗi 1 điểm sinh lực họ mất, bạn có thể rút 1 thẻ bài. Khi có người chơi trận vong bởi tự giảm sinh lực, bạn có thể bỏ 2 thẻ trên tay, hồi phục 1 sinh lực.')
	,('WEI037_1','Khuyến Tiến','Giai đoạn hành động 1 lần, bạn có thể giao 1 thẻ trên tay cho 1 người tại giai đoạn này đã chịu sát thương, sau đó lệnh người này chấp hành 1 [Quân Lệnh]. Nếu chấp hành, bạn rút 1 thẻ; nếu không chấp hành, bạn bổ sung bài trên tay đến bằng người hiện có só bài trên cay cao nhất (tối đa rút 5 thẻ).')
	,('WEI037_2','Tạc Vận','Giai đoạn hành động 1 lần, bạn có thể chọn 1 người không cùng thế lực với bạn tính toán khoảng cách > 1, đồng thời bỏ X thẻ bài trên tay (X là khoảng cách bạn tính toán đến họ -1), lệnh lượt nàu bạn tính toán khoảng cách đến người đó là 1. Sau đó bạn gây 1 sát thương cho người đó.')
	,('WEI038_1','Quyết Tuyệt','Bạn giết người cùng thế lực có thể bỏ qua chấp hành thưởng phạt. Bắt đầu giai đoạn bỏ bài, bạn có thể tự giảm 1 sinh lực, sau khi kết thúc giai đoạn này, nếu trong giai đoạn này bạn có bỏ bài, bạn lệnh toàn bộ người khác lần lượt chọn 1 mục:\n\t1. Đem X thẻ trên tay đưa vào chồng bài bỏ (X là số thẻ bạn bỏ)\n\t2. Lệnh bạn gây 1 sát thương lên họ.')
	,('WEI038_2','Phương Viên','\t1. Nếu bạn là người vây công, giới hạn trữ bài của bạn và người vây công khác trong quan hệ vây công này +1, giới hạn trữ bài của người bị vây công này -1.\n\t2. Nếu bạn là người bị vây công, giai đoạn kết thúc, bạn chọn 1 người vây công trong quan hệ vây công này và bạn xem như sử dụng 1 thẻ [Sát] lên họ.')
	,('WEI039_1','Thám Phong','Bắt đầu giai đoạn chuẩn bị, bạn có thể bỏ đi 1 thẻ bài trong vùng chơi của 1 người chơi ẩn danh hoặc không cùng thế lực với bạn. Nếu làm vậy, họ có thể lựa chọn 1 giai đoạn trừ giai đoạn chuẩn bị và lệnh bạn gây 1 sát thương hệ Hỏa cho họ, sau đó bạn bỏ qua giai đoạn được chọn ở lượt này.')
	,('WEI040_1','Đạm Lạc','Sau khi bạn trở thành mục tiêu của thẻ Cẩm Nang, nếu bạn không phải mục tiêu duy nhất của thẻ bài này, bạn có thể rút 1 thẻ bài, sau đó lệnh thẻ bài này vô hiệu với bạn.')
	,('WEI040_2','Kê Lặc','Sau khi bạn nhận sát thương, bạn có thể gọi tên 1 loại bài. Nguồn gây sát thương không thể dùng, đánh ra và bỏ bài thuộc loại bài mà bạn đã gọi.')
	,('WEI041_1','Linh Tuệ','Mỗi lượt tối đa 2 lần, khi có 1 người chơi rơi vào trạng thái hấp hối, bạn có thể xem 3 thẻ dưới đáy chồng bài rút, sau đó thu lấy 1 thẻ lên tay, 2 thẻ còn lại đưa vào chồng bài bỏ.')
	,('WEI041_2','Hiệt Sách','Sau khi bạn nhận 1 điểm sát thương, bạn có thể chọn 1 người khác, lệnh kỹ năng Phi Tỏa Định Kỹ của họ bị vô hiệu đến hết lượt.')
	,('WEI042_1','Liệt Vi','\t1. Khi bạn sử dụng 1 thẻ Vũ Khí/ Tọa Kỵ, bạn rút 1 thẻ\n\t2. Khi người khác cùng thế lực sử dụng 1 thẻ Vũ Khí/ Tọa Kỵ, bạn và họ rút 1 thẻ\n\t3. Sau khi bạn nhận sát thương, nếu số sát thương bạn nhận hơn 1, bạn lật úp tướng này.')
	,('WEI042_2','Tỏa Nhuệ','Bắt đầu lượt của 1 người chơi khác, bạn có thể lật mở thẻ tướng này, bạn gây 1 điểm sát thương đồng thời thu lấy X thẻ bài của họ (X là số sinh lực hiện tại của họ).')
	,('WEI043_1','Thiên Sủng','Giai đoạn rút bài, bạn sửa thành lật mở 4 thẻ từ đỉnh chồng bài rút, sau đó lựa chọn 1 mục:\n\t1. Bạn thu lấy 1 thẻ sắc Đỏ và 1 thẻ sắc Đen, những thẻ còn lại tiến vào chồng bài bỏ\n\t2. Bạn chọn 1 màu và thu tất cả thẻ cùng màu đó.')
	,('WEI043_2','Tôn Vị','Khi kết thúc lượt của bạn, nếu bạn không có bỏ bài ở giai đoạn bỏ bài lượt này, bạn có thể hô tên 1 màu, cho đến khi bắt đầu lượt tiếp theo của bạn, khi bạn trong 1 lần mất đi thẻ bài trên tay, nếu thẻ đó cùng màu với màu bạn đọc, bạn rút 1 thẻ.')
	,('WEI044_1','Cổ Thiệt','Giai đoạn hành động giới hạn 1 lần, bạn có thê rđấu điểm với 1 người khác; Nếu bạn thắng, bạn xem như sử dụng [Dĩ Dật Đãi Lao]; Nếu bạn không thắng, bạn thu 1 thẻ của 1 người chơi.')
	,('WEI044_2','Kích Từ','Khi bạn đấu điểm mà không thắng, nếu bạn phát động kỹ năng này:\n\t1. Trong lượt của bạn, bạn nhận 1 sát thương từ người đấu điểm còn lại, sau đó rút 3 thẻ bài\n\t2. Ngoài lượt của bạn, bạn chọn thu 1 thẻ Cẩm Nang trong chồng bài bỏ có điểm số nhỏ hơn thẻ đấu điểm của bạn.')
	,('WEI045_1','Thiện Giới','Giai đoạn hành động 1 lần, bạn có thể bỏ 1 thẻ, sau đó thu lấy 1 thẻ Vũ Khí của 1 người chơi khác, sau đó nếu bạn sử dụng vũ khí thu được bằng cách này, sát thương đầu tiên bạn gây ra cho cho 1 người không trang bị Vũ Khí +1.')
	,('WEI045_2','Dị Dũng','Trong lượt của bạn,\n\t1. Nếu sinh lực của bạn nhỏ hơn 3, bạn lệnh tất cả phòng cụ trên bàn chơi đều vô hiệu\n\t2. Nếu sinh lực của bạn nhỏ hơn 2, thẻ sát của bạn cần 2 [Né] để triệt tiêu.')
	,('WEI046_1','Lăng Nhân','Mỗi lượt giới hạn một lần, sau khi bạn sử dụng thẻ bài gây sát thương chỉ định 1 mục tiêu, nếu số lượng bài trên tay của mục tiêu đó ≤ bài trên tay bạn, bạn có thể rút 2 thẻ hoặc lệnh sát thương của thẻ bài đó lên mục tiêu +1.')
	,('WEI046_2','Phục Gian','Giai đoạn chuẩn bị và giai đoạn kết thúc, bạn có thể xem như sử dụng 1 thẻ [Tri Bỉ Tri Kỷ] lên 1 người chơi có bài trên tay ≤ bài trên tay bạn.')
	,('WEI047_1','Thiện Giáp','Bắt đầu giai đoạn hành động, bạn có thể rút thêm X thẻ (X là số người cùng thế lực với bạn), sau đó bạn bỏ đi 1 thẻ, nếu thẻ bỏ đi là Trang Bị, bạn xem như sử dụng 1 thẻ [Sát].')
	,('WEI048_1','Xưng Tượng','Sau khi bạn nhận sát thương ngoài lượt, bạn có thể lật mở ra 4 thẻ trên đỉnh chồng bài, sau đó thu lấy tùy ý số lượng thẻ bài có tổng điểm ≤ 13.')
	,('WEI048_2','Nhân Tâm','Khi người chơi khác nhận sát thương, nếu sinh lực người đó là 1, bạn có thể đặt chồng tướng và bỏ 1 thẻ Trang Bị, nếu bạn làm thế, ngăn chặn sát thương này.')
	,('WEI049_1','Ty Địch','Sau khi người cùng thế lực với bạn nhận sát thương, nếu họ có bài và số loại bài "Ngự" của bạn nhỏ hơn 3, bạn có thể phát động kỹ năng, họ có thể đặt ngửa 1 thẻ lên tướng bạn, gọi là "Ngự" (không được chọn thẻ cùng loại với thẻ "Ngự" đã có).\nKhi bắt đầu lượt của người thế lực khác, bạn có thể di dời tối đa 3 "Ngự", đưa ra số lựa chọn tương ứng:\n\t1. Bạn chọn 1 loại bài tương ứng với loại bài của "Ngự" vừa di dời, họ trong lượt không thể sử dụng loại bài này\n\t\n\t2. Bạn chọn 1 kỹ năng của thẻ tướng đã lật mở của họ, vô hiệu kỹ năng đó trong lượt này\n\t3. Bạn lệnh họ chọn 1 người khác cùng thế lực với bạn, người đó hồi 1 sinh lực')
	,('WEI050_1','Bình Giáng','Sau khi bạn sử dụng [Tri Bỉ Tri Kỷ] chỉ định mục tiêu, bạn có thể rút 1 thẻ. Khi bạn sử dụng [Tri Bỉ Tri Kỷ] trong lượt, bạn có thể lệnh 1 người khác cùng xem với bán. Sau khi 1 người khác sử dụng [Tri Bỉ Tri Kỷ] kết toán xong, xem như bạn cũng sử dụng 1 thẻ [Tri Bỉ Tri Kỷ] lên cùng mục tiêu đó.')
	,('WEI050_2','Giới Trung','Mỗi lượt giới hạn 1 lần, khi người cùng thế lực với bạn gây sát thương đối với người khác, nguồn sát thương có thể chặn sát thương này lại, sửa thành bỏ đi tối đa X thẻ (X là số người cùng thế lực với bạn và tối đa là 3). Nếu chỉ bỏ đi 1 thẻ, bạn xem như sử dụng [Tri Bỉ Tri Kỷ].')
	,('SHU001_1','Nhân Đức','Giai đoạn hành động, bạn có thể đem tùy ý lượng thẻ bài trong tay giao cho 1 người khác chưa nhận bài từ kỹ năng này trong giai đoạn này. Nếu vậy khi tổng số thẻ bài bạn giao trong lượt này bằng 2 hoặc nhiều hơn, bạn có thể xem như sử dụng 1 thẻ Cơ Bản bất kỳ.')
	,('SHU002_1','Hưởng Lạc','Sau khi bạn trở thành mục tiêu sử dụng [Sát] của người khác, người đó chọn 1 mục:\n\t- Bỏ 1 lá cơ bản.\n\t- Thẻ [Sát] này vô hiệu với bạn.')
	,('SHU002_2','Phóng Quyền','Trước khi bắt đầu giai đoạn hành động, bạn có thể bỏ qua giai đoạn này, nếu làm vậy, kết thúc lượt hiện tại, bạn có thể bỏ 1 lá bài trên tay, lệnh 1 người chơi khác chấp hành 1 lượt chơi.')
	,('SHU003_1','Bát Trận','Nếu vùng trang bị của bạn không có Phòng Cụ, bạn xem như đang trang bị [Bát Quái Đồ]')
	,('SHU003_2','Khán Phá','Bạn có thể sử dụng thẻ bài sắc Đen trên tay xem như thẻ [Vô Giải Khả Kích].')
	,('SHU003_3','Hỏa Kế','Giai đoạn hành động, bạn có thể sử dụng thẻ bài sắc Đỏ trên tay xem như thẻ [Hỏa Công].')
	,('SHU004_1','Liên Hoàn','Giai đoạn hành động, bạn có thể đem 1 thẻ chất ♣️ trên tay xem như sử dụng thẻ [Thiếc Tác Liên Hoàn] hoặc "Trọng Chú" thẻ đó.')
	,('SHU004_2','Niết Bàn','Khi bạn trong trạng thái hấp hối, bạn có thể bỏ hết tất cả thẻ bài trong vùng chơi của bạn, sau đó khôi phục trạng thái ban đầu và xoá mọi trạng thái bất lợi, bạn rút 3 thẻ bài và hồi phục cho bản thân đến khi đạt đủ 3 điểm sinh lực.')
	,('SHU005_1','Võ Thánh','Bạn có thể sử dụng thẻ bài Sắc Đỏ xem như thẻ [Sát]. Bạn sử dụng thẻ [Sát] chất ♦️ không hạn chế khoảng cách.')
	,('SHU006_1','Bào Hao','Bạn sử dụng thẻ [Sát] không giới hạn số lượng. Trong 1 lượt khi bạn sử dụng đến thẻ [Sát] thứ 2, bạn rút thêm 1 thẻ bài.')
	,('SHU007_1','Long Đảm','Bạn có thể sử dụng hoặc đánh ra thẻ [Sát] như [Né] và ngược lại. Nếu theo cách này:\n\t- Thẻ [Sát] của bạn bị triệt tiêu bởi thẻ bài [Né] của người chơi. Bạn có thể gây 1 sát thương cho người khác ngoài họ.\n\t- Thẻ [Né] của bạn triệt tiêu thẻ [Sát] của 1 người chơi, bạn có thể lệnh 1 người chơi khác ngoài họ hồi phục 1 sinh lực.')
	,('SHU008_1','Mã Thuật','Bạn tính khoảng cách đến người khác -1.')
	,('SHU008_2','Thiết Kỵ','Sau khi bạn sử dụng thẻ bài [Sát] chỉ định 1 người chơi khác làm mục tiêu, bạn có thể tiến hành phán xét. Sau đó vô hiệu tất cả kỹ năng không phải Tỏa Định Kỹ của 1 võ tướng đã lật mở của họ trong lượt này. Trừ phi họ bỏ đi 1 thẻ cùng chất với thẻ phán xét, nếu không họ không thể dùng thẻ [Né].')
	,('SHU009_1','Thục Thận','Sau khi bạn hồi phục 1 sinh lực hoặc cùng lúc mất đi nhiều hơn X thẻ bài (X là lượng sinh lực hiện tại của bạn), bạn có thể lệnh cho 1 người chơi khác cùng thế lực rút 1 thẻ từ chồng bài.')
	,('SHU009_2','Thần Trí','Giai đoạn chuẩn bị, bạn có thể bỏ đi tất cả bài trên tay. Nếu bằng cách này bạn bỏ đi số lượng thẻ bài không nhỏ hơn giá trị sinh lực hiện tại, bạn hồi phục 1 sinh lực.')
	,('SHU010_1','Không Thành','Nếu bạn không có bài trên tay, khi bạn trở thành mục tiêu của thẻ [Sát] hoặc [Quyết Đấu], huỷ bỏ nó. Ngoài lượt của bạn, bạn đem thẻ bài được người khác giao đặt lên thẻ tướng, gọi là [Cầm]. Bắt đầu giai đoạn rút bài, bạn thu lấy thẻ [Cầm].')
	,('SHU010_2','Quan Tinh','Bắt đầu giai đoạn chuẩn bị, bạn có thể xem trước X thẻ đầu chồng bài (X là số người chơi trên bàn, tối đa 5). Sau đó đem tuỳ ý lượng thẻ bài và trình tự đặt lên đầu chồng bài hoặc đáy chồng bài.')
	,('SHU011_1','Cự Tượng','[Nam Man Nhập Xâm] vô hiệu đối với bạn. Sau khi có người khác sử dụng thẻ [Nam Man Nhập Xâm] và kết toán tiến vào chồng bài bỏ, bạn thu lấy thẻ bài đó.')
	,('SHU011_2','Liệt Nhận','Sau khi bạn sử dụng thẻ bài [Sát] đối với mục tiêu tạo thành sát thương, bạn có thể cùng họ đấu điểm. Nếu như bạn thắng, bạn thu lấy 1 thẻ bài của họ.')
	,('SHU012_1','Hoạ Thủ','[Nam Man Xâm Nhập] vô hiệu đối với bạn. Sau khi có người sử dụng thẻ bài [Nam Man Xâm Nhập] chỉ định mục tiêu, bạn thành nguồn sát thương của thẻ bài này.')
	,('SHU012_2','Tái Khởi','Kết thúc giai đoạn bỏ bài, bạn có thể lệnh cho tối đa X người chơi cùng thế lực với bạn lần lượt lựa chọn 1 mục:\n\t1. Rút 1 thẻ bài.\n\t2. Lệnh bạn hồi phục 1 sinh lực.\n(X là số thẻ bài sắc Đỏ đi vào chồng bài bỏ trong lượt này).')
	,('SHU013_1','Kỳ Tài','Bạn sử dụng thẻ Cẩm Nang không hạn chế khoảng cách.')
	,('SHU013_2','Tập Trí','Khi bạn sử dụng 1 thẻ bài Cẩm Nang phổ thông (không chuyển hoá và không phải thẻ bài Ảo), bạn có thể rút 1 thẻ.')
	,('SHU014_1','Cuồng Cốt','Sau khi bạn đối với 1 người chơi trong khoảng cách 1 tạo thành 1 sát thương, bạn có thể chọn hồi phục 1 sinh lực hoặc rút 1 thẻ bài.')
	,('SHU015_1','Liệt Cung','Sau khi bạn trong giai đoạn hành động sử dụng thẻ [Sát] và chỉ định 1 mục tiêu. Nếu số bài trên tay của mục:\n\t1. Lớn hơn hoặc bằng sinh lực hiện tại của bạn;\n\t2. Nhỏ hơn hoặc bằng phạm vi công kích của bạn;\nNếu thỏa 1 mục trên, bạn có thể lệnh họ không thể hưởng ứng thẻ [Sát] này, nếu thỏa cả 2 mục trên, bạn có thể lệnh thêm cho sát thương thẻ [Sát] này +1.')
	,('SHU016_1','Truân Trữ','Giai đoạn rút bài, bạn có thể rút thêm 2 thẻ bài. Sau đó, bạn đặt từ 1 đến 2 thẻ trên tay lên tướng, gọi là "Lương". Nếu bạn làm thế, trong lượt này bạn không thể sử dụng [Sát].')
	,('SHU016_2','Thâu Lương','Giai đoạn kết thúc của 1 người chơi cùng thế lực với bạn, nếu khoảng cách giữa bạn và họ không lớn hơn X (X là số thẻ "Lương" đặt trên tướng này), bạn có thể di trừ 1 "Lương", lệnh họ rút 2 thẻ bài.')
	,('SHU017_1','Khiêu Hấn','Giai đoạn hành động 1 lần. Bạn có thể lệnh cho 1 người khác có phạm vi công kích đến bạn sử dụng [Sát] lên bạn, nếu không bạn bỏ đi 1 thẻ bài của họ.')
	,('SHU017_2','Thiên Phúc','Trong lượt của những người chơi có quan hệ đội hình, bạn nhận kỹ năng 『Khán Phá』')
	,('SHU017_3','Di Chí','Nếu Chủ Tướng của bạn có 『Quan Tinh』, kỹ năng này sửa số thẻ xem luôn là 5. Nếu không, bạn nhận kỹ năng này.')
	,('SHU018_1','Sinh Tức','Giai đoạn kết thúc, nếu trong lượt này bạn không gây ra sát thương, bạn có thể rút 2 thẻ bài')
	,('SHU018_2','Thủ Thành','Khi 1 người chơi cùng thế lực mất đi thẻ bài cuối cùng trên tay ngoài lượt, bạn có thể lệnh cho họ rút 1 thẻ')
	,('SHU019_2','Tiềm Tập','Giai đoạn chuẩn bị, bạn có thể rút 1 thẻ bài, đồng thời chọn bỏ 1 thẻ bài. Sau đó bạn lệnh cho 1 người chơi có khoảng cách là 1, họ không thể dùng hoặc đánh ra thẻ bài trên tay có cùng màu với thẻ bài bạn bỏ.')
	,('SHU020_1','Khuê Tú','Sau khi bạn lật mở võ tướng này, bạn có thể rút 2 thẻ bài. Sau khi bạn loại bỏ võ tướng này, bạn có thể hồi phục 1 sinh lực.')
	,('SHU020_2','Tôn Tự','Giai đoạn hành động, bạn có thể loại bỏ võ tướng này. Nếu làm như thế, bạn chọn 1 người chơi và họ nhận kỹ năng 『Dũng Quyết』, nếu người đó không phải bạn, họ rút 2 thẻ.')
	,('SHU020_3','Dũng Quyết','Nếu người cùng thế lực trong giai đoạn hành động sử dụng thẻ đầu tiên là thẻ [Sát], sau khi thẻ [Sát] này kết toán và nhập vào chồng bài bỏ, bạn lệnh người đó thu lại thẻ [Sát] này.')
	,('SHU021_1','Tật Lê','Trong 1 lượt của 1 người chơi, khi bạn sử dụng hoặc đánh ra thẻ bài. Nếu tổng số thẻ bạn sử dụng hoặc đánh ra trong lượt này là X, bạn rút X thẻ bài (X là phạm vi công kích của bạn).')
	,('SHU022_1','Tán Dao','Một lần trong giai đoạn hành động, bạn có thể bỏ đi 1 thẻ bài và chọn 1 người có sinh lực nhiều nhất, bạn gây 1 sát thương cho họ.')
	,('SHU022_2','Chế Man','Khi bạn gây sát thương cho 1 người chơi khác, bạn có thể chặn sát thương này lại. Nếu vậy, bạn thu lấy 1 thẻ bài trong vùng trang bị hoặc phán xét của họ. Nếu họ cùng thế lực, họ có thể thay đổi Phó Tướng.')
	,('SHU023_1','Tướng Lược','Giai đoạn hành động, bạn có thể phát động 1 lần [Quân Lệnh], sau đó thực hiện triệu hoán thế lực, người chơi khác cùng thế lực với bạn có thể chấp hành [Quân Lệnh] này (người chơi Ẩn Danh cùng thế lực có thể mở tướng hưởng ứng ở thời điểm này). Bạn cùng với người chấp hành [Quân Lệnh] được tăng 1 giới hạn sinh lực ; Sau đó bạn được rút X thẻ bài (X là số người được hồi sinh lực).')
	,('SHU024_1','Huyễn Hoặc','Một lần trong giai đoạn hành động của người khác cùng thế lực, họ có thể giao cho bạn 1 thẻ bài trên tay, sau đó họ bỏ đi 1 thẻ và chọn sở hữu 1 trong các kỹ năng chưa có trên bàn chơi cho đến hết lượt sau: 『Võ Thánh』, 『Bào Hao』, 『Long Đảm』, 『Thiết Kỵ』, 『Liệt Cung』, 『Cuồng Cốt』.')
	,('SHU024_2','Ân Oán','Khi người khác dùng [Đào] lên bạn, bạn lệnh họ rút 1 thẻ bài. Sau khi bạn nhận sát thương, bạn lệnh nguồn sát thương giao 1 thẻ bài trên tay cho bạn hoặc tự mất 1 sinh lực.')
	,('SHU025_1','Giản Lượng','Bắt đầu giai đoạn rút bài, nếu trên bàn chơi bạn có ít bài trên tay nhất. Bạn có thể lệnh cho tất cả người chơi cùng thế lực mỗi người rút 1 thẻ.')
	,('SHU025_2','Ngụy Minh','Bắt đầu giai đoạn hành động giới hạn 1 lần, bạn có thể thu lấy X thẻ bài trên tay của 1 người chơi khác (X là số sinh lực hiện tại của bạn), sau đó giao lại cho lượng bài tương đương.\n\nTung Hoành: Người đó nhận kỹ năng 『Nguy Minh』 đến khi kết thúc lượt sau của họ, đồng thời kỹ năng thay đổi mô tả "X thành 1".')
	,('SHU026_1','Khí Ngạo','Mỗi lượt 1 người giới hạn 2 lần, sau khi bạn trở thành mục tiêu dùng bài của người không cùng thế lực, bạn có thể bỏ 1 thẻ bài của người đó, sau đó bạn tự bỏ 1 thẻ bài.')
	,('SHU026_2','Thừa Thưởng','Giai đoạn hành động 1 lần, sau khi bạn sử dụng bài kết toán xong, nếu thẻ này có chỉ định người không cùng thế lực làm mục tiêu và thẻ này chưa gây sát thương, bạn có thể rút 1 thẻ. Nếu vậy, giai đoạn này bạn có thể sử dụng 1 thẻ trên tay như 1 thẻ Cơ Bản khác hoặc thẻ Cẩm Nang phổ thông khác có cùng chất và điểm số như thẻ bạn đã sử dụng lần này.')
	,('SHU027_1','Đương Tiên','Khi bạn lần đầu lật mở thẻ tướng này, bạn thu lấy 1 thẻ tiêu ký "Tiên Phong"; Khi bắt đầu lượt chơi của bạn, bạn chấp hành thêm 1 giai đoạn hành động.')
	,('SHU028_1','Định Khoa','Mỗi lượt giới hạn 1 lần, sau khi 1 người cùng thế lực mất đi thẻ bài ngoài lượt của họ không vì sử dụng/đánh ra, bạn có thể chọn: 1. Bạn giao cho họ 1 thẻ trên tay; 2. Bạn lệnh người đang có lượt bỏ đi 1 thẻ trên tay. Sau đó nếu số tiêu ký "Khuyết Ngọc" của bạn ít hơn giới hạn sinh lực, bạn thu lấy tiêu ký "Khuyết Ngọc".')
	,('SHU028_2','Cấp Viện','Khi 1 người rơi vào trạng thái hấp hối hoặc sau khi bạn giao bài cho người khác thông qua 『Định Khoa』, bạn có thể lệnh họ rút 1 thẻ.')
	,('SHU029_1','Mục Minh','Giai đoạn hành động giới hạn 1 lần, bạn có thể chuyển hóa 1 thẻ chất ♥️ trên tay sử dụng như [Viễn Giao Cận Công] hoặc [Lục Lực Đồng Tâm].')
	,('SHU029_2','Nạp Man','Khi người khác sử dụng thẻ bài sắc Đen có chỉ định nhiều mục tiêu, bạn có thể tiến hành phán xét, nếu kết quả phán xét không phải chất ♠️, bạn chọn 1 mục:\n\t1. Lệnh 1 người khác trở thành mục tiêu của thẻ bài đó (Không bị giới hạn khoảng cách);\n\t2. Hủy bỏ mục tiêu đối với 1 mục tiêu của thẻ bài đó.')
	,('SHU030_1','Vãng Liệt','Giai đoạn hành động, bạn sử dụng thẻ bài đầu tiên không hạn chế khoảng cách. Khi bạn dùng bài trong giai đoạn hành động, bạn có thể lệnh cho thẻ bài này không thể hưởng ứng. Nếu bạn làm như thế, giai đoạn này bạn không thể dùng bài được nữa.')
	,('SHU031_1','Chinh Phong','Giai đoạn chuẩn bị của người cùng thế lực, bạn có thể sử dụng 1 thẻ [Sát].')
	,('SHU031_2','Lữ Tiến','Một lần mỗi lượt đối với mỗi người, sau khi thẻ [Sát] của bạn gây ra sát thương, bạn có thể giao thẻ [Sát] này cho 1 người khác, nếu họ là Nữ, họ nhận 1 tiêu ký "Khuyết Ngọc".')
	,('SHU031_3','Mộ Dưỡng','Giai đoạn kết thúc, bạn có thể mở 2 thẻ trên đầu chồng bài rút, thu lấy bài sắc Đỏ và thẻ [Sát] trong số đó.')
	,('SHU032_1','Xu Doanh','Phạm vi công kích và giới hạn trữ bài của bạn +1. Khi xét quan hệ trận pháp, bạn được tính là 2 người ngồi liền kề nhau.')
	,('SHU032_2','Hổ Dục','Một lần mỗi lượt đối với mỗi người, sau khi kết toán thẻ [Sát] do người cùng đội hình với bạn sử dụng, nếu mục tiêu có người khác cùng thế lực tạo đội hình liền kề, thì bạn có thể dùng thêm 1 thẻ [Sát] (không tính vào số lần sử dụng trong lượt) lên 1 người khác ở hàng liền kề đó.')
	,('SHU033_1','Kiếm Ca','Bạn có thể đem 1 thẻ Phi Cơ Bản để sử dụng hoặc đánh ra như thẻ [Sát].')
	,('SHU033_2','Tiềm Học','Khi kết thúc lượt của 1 người, bạn có thể thu lấy X thẻ không phải [Hiệp Thiên Tử Dĩ Lệnh Chư Hầu] trong những thẻ bài tiến vào chồng bài bỏ lượt này, ưu tiên chọn thẻ Phi Cơ Bản).')
	,('SHU033_3','Trục Cốc','Sau khi người cùng thế lực thay đổi Phó Tướng hoặc trận vong, nếu thẻ tướng này là:\n\t- Chủ tướng và thẻ phó tướng là đơn thế lực, bạn hoàn toàn đổi phó tướng với chủ tướng;\n\t- Phó tướng, bạn thoát trạng thái liên hoàn. Sau đó bạn thay đổi Phó Tướng.')
	,('SHU034_1','Dụ Ngôn','Trong lượt 1 lần, sau khi bài của bạn đi vào chồng bài bỏ do bạn bỏ đi, bạn có thể lật mở 4 thẻ đầu chồng bài rút, thu lấy những thẻ không cùng chất với thẻ bài bạn bỏ.')
	,('SHU034_2','Truy Hoàn','Giai đoạn kết thúc, bạn có thể chọn tối đa 2 người: lệnh 1 người nhận tiêu ký "Thương Hại", còn lại nhận "Khí Bài" (người chơi không biết được tiêu ký, nếu tiêu ký họ bỏ là:\n\t- Thương Hại: họ gây 1 sát thương cho nguồn.\n\t- Khí Bài: nguồn sát thương bỏ 2 thẻ trên tay.')
	,('SHU035_1','Quắc Thủ','Mỗi vòng chơi giới hạn 1 lần, bắt đầu lượt của người khác, bạn có thể bỏ 1 thẻ bài, thu 1 thẻ trong vùng chơi của họ (nếu họ là Nữ, bạn không cần bỏ bài, đồng thời đổi thành họ giao 1 thẻ trong vùng chơi cho bạn), sau đó tiến hành »Hiến Sách« với họ. Nếu làm như thế, giai đoạn hành động của họ chọn 1 mục:\n\t1. Lệnh 1 người không cùng thế lực chấp hành [Quân Lệnh]\n\t2. Bỏ đi [Quân Lệnh], lượt này tăng 1 lần dùng [Sát] và thẻ [Sát] sẽ không giới hạn khoảng cách.')
	,('SHU035_2','Giáo Hoá','Giai đoạn rút bài của người cùng thế lực, nếu họ có ít bài trên tay nhất, bạn có thể sửa thành lệnh họ »Kiểm Sách« đồng thời thu lấy 3 thẻ không cùng phân loại.')
	,('SHU036_1','Tri Mệnh','Bắt đầu giai đoạn chuẩn bị và kết thúc, bạn có thể rút 1 thẻ, sau đó đặt 1 thẻ trên tay lên đầu chồng bài rút.')
	,('SHU036_2','Tinh Bặc','Cuối giai đoạn kết thúc, bạn mở 3 thẻ trên đầu chồng bài rút sau đó đưa vào chồng bài bỏ, bạn chọn 1 người khác cùng thể lực đang bị thương căn cứ theo số thẻ sắc Đỏ được hiệu quả tương ứng ở lượt sau:\n\t- 3 thẻ, họ rút thêm 1 thẻ và tăng 1 lần dùng [Sát];\n\t- 2 thẻ, họ hồi 1 sinh lực và giảm 1 lần dùng [Sát].')
	,('SHU037_1','Khiêm Sách','Sau khi người cùng thế lực với bạn sử dụng thẻ Cẩm Nang chỉ định mục tiêu, bạn có thể lệnh những mục tiêu thuộc Đại Thế Lực không thể hưởng ứng thẻ này.')
	,('SHU037_2','Cử Tiến','Khi 1 người cùng thế lực với bạn tiến vào trạng thái hấp hối, bạn lệnh họ hồi phục đến còn 1 sinh lực, sau đó bạn thay đổi Phó Tướng 1 lần.')
	,('SHU038_1','Thống Độ','Giai đoạn kết thúc của người chơi cùng thế lực, người đó có thể rút X thẻ (X là số thẻ người đó bỏ đi trong giai đoạn bỏ bài, tối đa là 3).')
	,('SHU038_2','Thanh Ẩn','Giai đoạn hành động, bạn có thể lệnh cho tất cả người chơi cùng thế lực với bạn hồi phục lượng sinh lực đến tối đa, sau đó bạn di trừ thẻ tướng này.')
	,('SHU039_1','Kháng Duệ','Giai đoạn hành động của người cùng thể lực giới hạn 1 lần, khi họ dùng bài chỉ định người khác là mục tiêu duy nhất, bạn có thể huỷ bỏ mục tiêu của thẻ đó, lệnh họ chọn 1 mục:\n\t1. Họ bổ sung bài trên tay đến khi có X thẻ (X là giới hạn sinh lực của họ), những người khác trong giai đoạn này không thể trở thành mục tiêu của thẻ bài họ sử dụng.\n\t2. Nếu không có người trong trạng thái hấp hối, họ lệnh mục tiêu ban đầu của thẻ đó xem như dùng [Quyết Đấu] với họ, số sát thương của thẻ này +1.')
	,('SHU040_1','Căng Vĩ','Bắt đầu giai đoạn hành động, bạn có thể lệnh bản thân thực hiện 1 [Quân Lệnh]. Nếu chấp hành, bạn xem như sử dụng 1 thẻ [Sát] (không tính vào trong lượt). Nếu không, kết thúc giai đoạn này.')
	,('SHU040_2','Trúc Khoa','Khi bạn chấp hành [Quân Lệnh], bạn có thể tiến hành chọn lại [Quân Lệnh]. Khi bạn tiến vào trạng thái liên hoàn/chồng tướng, bạn có thể lệnh 1 người cùng thế lực hồi phục 1 sinh lực.')
	,('SHU040_3','Khuyến Giá','Sau khi bạn lần đầu lật mở tướng này, bạn thực hiện 1 lần triệu hoán thế lực, người lật mở tướng bởi kỹ năng này không trở thành Dã Tâm, người cùng thế lực với bạn rút 1 thẻ, người có 『Nhân Đức』 nhận kỹ năng 『Chương Vũ』và 『Thụ Việt』.')
	,('SHU041_1','Man Tự','[Nam Man Nhập Xâm] vô hiệu đối với bạn; Nếu 1 thẻ [Nam Man Nhập Xâm] được sử dụng và có gây sát thương, sau khi kết toán bạn rút 3 thẻ; Sau khi bạn lần đầu lật mở tướng này, xem như bạn sử dụng 1 thẻ [Nam Man Nhập Xâm].')
	,('SHU041_2','Hi Chính','Mỗi lượt giới hạn 1 lần, sau khi chỉ định mục tiêu cho thẻ [Sát], bạn có thể bỏ 1 thẻ cùng chất với thẻ [Sát], sửa thành bạn xem như sử dụng 1 thẻ [Nam Man Nhập Xâm] lên cùng mục tiêu đó. (thẻ [Sát] này có tính vào giới hạn sử dụng).')
	,('SHU042_1','Chiến Tuyệt','Giai đoạn hành động 1 lần, bạn có thể đem tất cả bài trên tay xem như [Quyết Đấu] để sử dụng. Sau khi thẻ [Quyết Đấu] này được kết toán, bạn có thể rút 1 thẻ, nếu bằng cách này không có ai nhận sát thương từ [Quyết Đấu], bạn có thể rút 1 thẻ.')
	,('SHU042_2','Cần Vương','\t1. Sát thương bạn nhận được từ thẻ [Sát]/[Quyết Đấu] sửa thành bạn tự giảm 1 sinh lực\n\t2 Sau khi bạn tự giảm sinh lực, bạn có thể lệnh 1 người rút 1 thẻ.')
	,('SHU043_1','Từ Ứng','Giai đoạn hành động giới hạn 2 lần, nếu bài trên tay của bạn lớn hơn 4 thẻ, bạn có thể bỏ hết bài trên tay. Nếu bạn làm thế, bạn có thể lệnh 1 người khác thu lấy 1 thẻ có điểm số cao nhất trong số thẻ bạn bỏ, sau đỏ bạn bổ sung bài trên tay sao cho đạt đủ 4 thẻ.')
	,('SHU043_2','Trần Tình','Khi bạn trận vong, bạn lệnh cho thưởng phạt của người giết bạn trở thành họ trọng chú tất cả bài trên tay, sau đó bạn chọn bỏ đi tối thiểu 1 thẻ trong vùng trang bị của họ.')
	,('SHU044_1','Phúc Miên','Giai đoạn hành động, bạn có thể bỏ đi tùy ý số thẻ bài có mang ký hiệu "Hợp/Liên", sau đó lệnh 1 người rút số lượng bài bằng đúng số thẻ đã bỏ; Mỗi lượt 1 lần, khi 1 người cùng thế lực với bạn thu được ít nhất 2 thẻ cùng một lúc ngoài lượt của họ, bạn có thể rút 1 thẻ bài.')
	,('SHU044_2','Quý Tướng','Nếu tướng này đang lật mở, giới hạn trữ bài của người chơi cùng thế lực +1.')
	,('SHU045_1','Hiệu Thủ','Giai đoạn kết thúc của 1 người chơi, nếu trong lượt này bạn đã từng hưởng ứng qua thẻ bài được sử dụng bởi người khác, bạn có thể chọn 1 mục:\n\t1. Bạn rút 1 thẻ\n\t2. Bạn lật mở 1 thẻ Cẩm Nang trên tay, sau đó rút 2 thẻ và đặt thẻ vừa lật mở lên đỉnh chồng bài rút.')
	,('SHU046_1','Tư Viện','Giai đoạn hành động 1 lần, bạn có thể lật mở và đưa ra các thẻ bài trên có tổng điểm bằng 13 giao cho 1 người khác cùng thế lực, sau đó lệnh họ hồi phục 1 sinh lực.')
	,('SHU046_2','Cự Cổ','Sau khi lật mở thẻ tướng này, bạn rút X thẻ; Giới hạn trữ bài của bạn +X (X là giới hạn sinh lực của bạn).')
	,('SHU047_1','Thậm Hiền','Mỗi lượt giới hạn 1 lần, sau khi 1 người chơi khác bỏ đi thẻ Cơ Bản ở ngoài lượt của bạn, bạn có thể rút 1 thẻ.')
	,('SHU047_2','Thương Vũ','Giai đoạn hành động 1 lần, bạn có thể tiến hành phán xét, sau đó trong lượt này, thẻ [Sát] tiếp theo bạn sử dụng có điểm số lớn hơn kết quả phán xét sẽ không bị tính vào số lần sử dụng trong lượt, và thẻ [Sát] tiếp theo có điểm số nhỏ hơn kết quả phán xét sẽ không bị giới hạn khoảng cách.')
	,('SHU048_1','Cường Thức','Bắt đầu giai đoạn hành động, bạn có thể lật mở 1 thẻ trên tay của 1 người khác, nếu làm vậy, cứ mỗi khi bạn sử dụng bài cùng loại với nó trong giai đoạn này, bạn có thể rút 1 thẻ (tối đa rút 3 thẻ).')
	,('SHU048_2','Hiến Đồ','Bắt đầu giai đoạn hành động của 1 người khác, bạn có thể rút 2 thẻ, sau đó giao cho họ 2 thẻ. Khi kết thúc giai đoạn này, nếu trong giai đoạn này họ không giết bất kì người chơi nào, bạn tự giảm 1 điểm sinh lực.')
	,('SHU049_1','Khám Tập','Giai đoạn hành động giới hạn 1 lần, bạn có thể lật mở hết bài trên tay bạn, nếu mỗi thẻ bài có chất khác nhau, bạn rút 2 thẻ, sau đó nếu bởi vậy các thẻ trên tay bạn đủ 4 chất, bạn bỏ qua giai đoạn bỏ bài lượt này.')
	,('SHU049_2','Khiêm Chính','Mỗi lượt 1 người giới hạn 1 lần, khi bạn trở thành mục tiêu của thẻ cẩm nang phổ thông hoặc [Sát] do người khác sử dụng, bạn có thể Trọng Chú 2 thẻ. Nếu những thẻ Trọng Chú này khác loại với thẻ họ sử dụng, sau khi thẻ đó kết toán xong và đi vào chồng bài bỏ, bạn có thể thu lấy thẻ đó.')
	,('SHU050_1','Cự Chiến','\t1. Dương: Sau khi bạn trở thành mục tiêu của [Sát], bạn có thể cùng người sử dụng rút X thẻ bài, sau đó nếu họ đang có tướng lật mở, bạn có thể lật úp 1 thẻ tướng của họ, lượt này họ không thể lật mở thẻ tướng đó.\n\t2. Âm: Sau khi bạn sử dụng [Sát] chỉ định mục tiêu, bạn có thể thu lấy X thẻ bài của mục tiêu (X là số sinh lực đã mất của bạn, tối thiểu là 1). Sau đó nếu thẻ tướng bạn đang lật mở, họ có thể úp 1 thẻ tướng của bạn, lượt này bạn không thể lật mở thẻ tướng đó.')
	,('WU001_1','Chế Hành','Một lần trong giai đoạn hành động, bạn có thể bỏ đi nhiều nhất X thẻ bài (X là giới hạn sinh lực của bạn). Sau đó bạn rút lại số thẻ tương ứng.')
	,('WU002_1','Anh Hồn','Giai đoạn chuẩn bị, bạn có thể chọn 1 người chơi khác và lựa chọn 1 mục sau:\n\t1. Lệnh họ rút X thẻ bài sau đó bỏ đi 1 thẻ bài.\n\t2. Lệnh họ rút 1 thẻ bài sau đó bỏ đi X thẻ bài.\n(X là số sinh lực mà bạn đã mất).')
	,('WU003_1','Anh Tư','Giai đoạn rút bài, bạn rút thêm 1 thẻ; Giới hạn trữ bài của bạn bằng với giới hạn sinh lực.')
	,('WU003_2','Phản Gián','Một lần trong giai đoạn hành động, bạn có thể mở ra 1 thẻ bài trên tay và giao cho 1 người khác. Bạn lệnh họ lựa chọn 1 mục:\n\t1. Mở toàn bộ thẻ bài trên tay và bỏ đi tất cả thẻ cùng chất mà bạn mở ra.\n\t2. Tự giảm đi 1 sinh lực.')
	,('WU004_1','Hảo Thí','Giai đoạn rút bài, bạn có thể rút thêm 2 thẻ, khi kết thúc giai đoạn rút bài, nếu bài trên tay bạn hơn 5 thẻ, bạn đem 1 nửa số bài trên tay giao cho 1 người khác có ít bài trên tay nhất trên bàn chơi.')
	,('WU004_2','Kết Minh','Một lần trong giai đoạn hành động, bạn có thể chọn 2 người chơi khác và bỏ đi X thẻ (X là số bài chênh lệch trên tay giữa 2 người chơi đó). Sau đó, bạn lệnh họ hoán đổi bài trên tay cho nhau.')
	,('WU005_1','Bất Khuất','Khi bạn trong trạng thái hấp hối, bạn đem 1 lá bài trên đầu chồng bài đặt lên võ tướng này, gọi là "Sang". Nếu điểm số của lá "Sang" này so với từng lá "Sang" trước đó:\n\t- Đều khác nhau: bạn hồi phục sinh lực đến 1.\n\t- Trùng với 1 "Sang" khác bất kỳ: bạn loại bỏ lá này, sau đó tiếp tục xử lý trạng thái hấp hối.')
	,('WU005_2','Phấn Kích','Giai đoạn kết thúc của 1 người chơi, nếu người chơi đó không có bài trên tay, bạn có thể lệnh người chơi đó rút 2 lá bài, sau đó bạn tự giảm 1 sinh lực.')
	,('WU006_1','Kỳ Tập','Giai đoạn hành động, bạn có thể sử dụng thẻ bài sắc Đen xem như thẻ bài [Quá Hà Sách Kiều].')
	,('WU007_1','Khổ Nhục','Một lần trong giai đoạn hành động, bạn có thể bỏ đi 1 thẻ bài. Nếu làm như vậy, bạn tự mất đi 1 sinh lực, sau đó bạn rút 3 thẻ. Trong lượt hành động này của bạn, giới hạn sử dụng thẻ [Sát] trong giai đoạn này +1.')
	,('WU008_1','Đoản Binh','Khi bạn sử dụng thẻ [Sát] có thể chọn trong khoảng cách 1 thêm người khác làm mục tiêu. Thẻ [Sát] của bạn khi chỉ định 1 người chơi duy nhất cần 2 thẻ [Né] để triệt tiêu.')
	,('WU008_2','Phấn Tấn','Một lần trong giai đoạn hành động, bạn có thể bỏ đi 1 thẻ và chọn 1 người khác. Bạn tính khoảng cách đến họ luôn là 1 trong lượt này.')
	,('WU009_1','Quốc Sắc','Giai đoạn hành động, bạn có thể sử dụng thẻ bài chất ♦️ như thẻ [Lạc Bất Tư Thục].')
	,('WU009_2','Lưu Ly','Khi bạn trở thành mục tiêu của thẻ [Sát], bạn có thể bỏ 1 thẻ để chuyển mục tiêu của thẻ [Sát] sang 1 người khác (trừ mục tiêu ban đầu và nguồn thẻ [Sát] này) trong phạm vi công kích của bạn.')
	,('WU010_1','Hồng Nhan','Thẻ chất ♠️ của bạn xem như chất ♥️. Nếu trong vùng Trang Bị của bạn có thẻ chất ♥️, giới hạn trữ bài của bạn +1.')
	,('WU010_2','Thiên Hương','Khi bạn nhận sát thương, bạn có thể bỏ 1 thẻ chất ♥️ trên tay để chặn sát thương này lại và bạn lựa chọn 1 người chơi khác, bạn chọn 1 mục sau cho mục tiêu:\n\t1. Họ nhận 1 sát thương từ nguồn, sau khi kết toán sát thương, rút X thẻ bài (X là số sinh lực mục tiêu đã mất, nhiều nhất là 5);\n\t2. Họ tự giảm 1 sinh lực, sau đó thu lấy bài bạn vừa bỏ.\nMỗi lượt mỗi mục chọn chỉ được phát động 1 lần duy nhất.')
	,('WU011_1','Thiên Nghĩa','Giai đoạn hành động, bạn có thể đấu điểm với 1 người khác. Nếu bạn thắng, lượt này bạn sử dụng thẻ [Sát] không giới hạn khoảng cách, giới hạn số lần sử dụng và số mục tiêu của thẻ [Sát] +1; Nếu bạn không thắng, bạn không thể sử dụng thẻ [Sát] cho đến khi kết thúc lượt.')
	,('WU011_2','Hàm Chiến','Sau khi bạn đấu điểm, người không thắng có thể thu lấy 1 thẻ trong vùng trang bị của người còn lại.')
	,('WU012_1','Khắc Kỷ','Đầu giai đoạn bỏ bài, nếu bài trong giai đoạn hành động không sử dụng các thẻ bài khác màu sắc với nhau, giới hạn trữ bài trong lượt này của bạn +4.')
	,('WU012_2','Mưu Đoạn','Giai đoạn kết thúc, nếu bạn trong giai đoạn hành động đã sử dụng bài từ 4 chất khác nhau hoặc 3 loại bài khác nhau, bạn có thể di chuyển 1 thẻ bài trên bàn chơi.')
	,('WU013_1','Khiêm Tốn','Khi bạn trở thành mục tiêu duy nhất của thẻ bài Cẩm Nang do người khác sử dụng, nếu trên tướng bạn số thẻ "Tiết" ≤ 3, bạn hủy bỏ thẻ đó đồng thời đặt thẻ đó lên tướng này, gọi là "Tiết".')
	,('WU013_2','Độ Thế','Giai đoạn hành động giới hạn 1 lần, bạn có thể lựa chọn 1 mục:\n\t1. Đem 1 thẻ sắc Đỏ trên tay xem như thẻ [Dĩ Dật Đãi Lao] để sử dụng.\n\t2. Di trừ 3 thẻ "Tiết" đồng thời xem như sử dụng tùy ý 1 thẻ bài phân loại sát thương thuộc tính Hoả.')
	,('WU014_1','Trực Gián','Giai đoạn hành động, bạn có thể đem 1 lá Trang Bị trên tay đặt vào vị trí còn trống trong vùng Trang Bị của 1 người khác, sau đó bạn được rút 1 thẻ bài.')
	,('WU014_2','Cố Chính','Kết thúc giai đoạn bỏ bài của 1 người chơi khác, bạn có thể đem những thẻ trên tay được bỏ đi trong giai đoạn này trả 1 thẻ lên tay của họ. Nếu làm như thế, bạn thu lấy những thẻ bị bỏ đi còn lại trong giai đoạn này.')
	,('WU015_1','Kiêu Cơ','Khi bạn trong 1 lần mất đi thẻ trong khu vực Trang Bị. Nếu đang trong lượt của bạn, bạn rút 1 thẻ. Nếu không phải trong lượt của bạn, bạn rút 3 thẻ.')
	,('WU015_2','Kết Nhân','Một lần trong giai đoạn hành động, bạn có thể bỏ đi 2 thẻ bài trên tay và chọn 1 võ tướng Nam khác đang bị thương, bạn cùng họ hồi phục 1 sinh lực.')
	,('WU016_1','Độc Tiến','Giai đoạn rút bài, bạn có thể rút thêm X thẻ bài (X là số thẻ Trang Bị trong khu Trang Bị chia đôi làm tròn lên). Khi bạn lật đầu lật mở thẻ tướng này, nếu bạn là người đầu tiên của thế lực, bạn thu lấy 1 tiêu ký "Tiên Phong".')
	,('WU017_1','Thượng Nghĩa','Một lần trong giai đoạn hành động. Bạn có thể cho người khác xem toàn bộ bài trên tay. Sau đó bạn chọn:\n\t1. Bạn xem bài trên tay của họ và bỏ đi 1 thẻ sắc Đen.\n\t2. Bạn xem tất cả tướng úp của người chơi đó.')
	,('WU017_2','Điểu Tường','Trong trạng thái "Vây Công". Nếu bạn hoặc đồng đội trong quan bao vây sử dụng [Sát] chỉ định người không cùng thế lực bị vây công thì họ cần 2 [Né] để triệt tiêu thẻ [Sát] này.')
	,('WU018_1','Nghi Thành','Sau khi người cùng thế lực bạn trở thành mục tiêu của thẻ bài [Sát], bạn có thể lệnh cho họ rút 1 thẻ, sau đó họ có thể sử dụng ngay 1 thẻ trang bị trên tay. Nếu làm như thế, trước khi kết thúc lượt của người đang có lượt, họ bỏ cùng lúc X thẻ bài (X là số lần phát động 『Nghi Thành』 lên họ)')
	,('WU019_1','Kích Ngang','Sau khi bạn dùng hoặc trở thành mục tiêu của thẻ bài [Sát] sắc Đỏ hoặc [Quyết Đấu], bạn có thể rút 1 thẻ bài.')
	,('WU019_2','Ưng Dương','Sau khi thẻ đấu điểm được lật mở, bạn có thể +3 hoặc -3 cho thẻ đấu điểm của bạn.')
	,('WU019_3','Hồn Thương','Giai đoạn chuẩn bị, nếu sinh lực của bạn là 1, bạn có kỹ năng 『Anh Hồn』 và 『Anh Tư』 cho đến hết lượt này.')
	,('WU020_1','Đoạn Tiết','Một lần trong giai đoạn hành động. Bạn có thể lệnh cho X người khác không nằm trong trạng thái liên hoàn (xoay ngang võ tướng) rơi vào trạng thái liên hoàn, sau đó bạn xoay ngang võ tướng của bạn (X là số sinh lực đã mất của bạn, tối thiểu là 1).')
	,('WU020_2','Phấn Mệnh','Một lần trong giai đoạn hành động, nếu bạn trong trạng thái liên hoàn (xoay ngang võ tướng), bạn có thẻ bỏ đi 1 thẻ của tất cả người chơi đang xoay ngang võ tướng.')
	,('WU021_1','Toàn Lược','Sau khi bạn trong 1 lần mất đi thẻ trong khu vực Trang Bị, bạn có thể bỏ đi 1 thẻ bài của người chơi khác.')
	,('WU021_2','Dũng Tiến','Trong giai đoạn hành động, bạn có thể di chuyển lần lượt nhiều nhất 3 thẻ Trang Bị trên vùng chơi.')
	,('WU022_1','Điều Độ','Khi người cùng thế lực lần đầu sử dụng thẻ Trang Bị trong lượt, họ có thể rút 1 thẻ. Bắt đầu giai đoạn hành động, bạn có thể thu lấy 1 thẻ trong vùng Trang Bị của 1 người cùng thế lực, sau đó bạn có thể đem giao cho 1 người khác.')
	,('WU022_2','Điển Tài','Kết thúc giai đoạn hành động của người khác, nếu trong giai đoạn này số bài bạn mất đi không nhỏ hơn số sinh lực của bạn, bạn có thể bổ sung bài trên tay đến bằng giới hạn sinh lực. Sau đó, nếu bạn chưa thay đổi Phó tướng, bạn có thể thay đổi Phó Tướng 1 lần.')
	,('WU023_1','Bổ Ích','Một lần mỗi lượt, sau khi người chơi cùng thế lực thoát khỏi trạng thái hấp hối, bạn có thể lệnh nguồn sát thương chấp hành 1 [Quân Lệnh]. Nếu họ không chấp hành, bạn lệnh người vừa thoát khỏi hấp hối hồi phục 1 sinh lực.')
	,('WU023_2','Cam Lộ','Giai đoạn hành động 1 lần, bạn có thể chọn 2 người chơi có số thẻ bài trong vùng Trang Bị không cùng là 0 và chênh lệch không lớn hơn số sinh lực bạn đã mất. Lệnh họ hoán đổi bài trong khu Trang Bị với nhau.')
	,('WU024_1','Khắc Thủ','Khi bạn nhận sát thương, bạn có thể bỏ đi 2 thẻ bài cùng màu trên tay, lệnh sát thương này -1. Nếu không có người khác cùng thế lực, sau khi bạn cùng lúc bỏ đi 2 thẻ bài, nếu kết quả là sắc Đỏ, bạn rút 1 thẻ bài.')
	,('WU024_2','Quyết Yển','Giai đoạn chuẩn bị, bạn có thể chọn 1 vùng chơi của bạn có bài và sau đó bỏ hết bài trong vùng đó ở giai đoạn kết thúc. Đồng thời ứng với vùng chơi nhận hiệu quả tương ứng:\n\t- Vùng phán xét, bạn nhận kỹ năng 『Tập Trí』 và bỏ qua giai đoạn phán xét;\n\t- Vùng trang bị, giới hạn trữ bài +3 và bạn rút thêm 3 thẻ;\n\t- Vùng bài trên tay, giới hạn dùng [Sát] +3.')
	,('WU025_1','Ngọc Toái','Mỗi lượt giới hạn 1 lần, sau khi bạn trở thành mục tiêu của thẻ bài sắc Đen do người khác sử dụng; Nếu họ không cùng thế lực với bạn, bạn có thể tự giảm 1 sinh lực, sau đó bạn có thể tự giảm 1 sinh lực, sau đó bạn lựa chọn 1 mục:\n\t1. Họ bỏ đi X thẻ trên tay (X là giới hạn sinh lực của họ).\n\t2. Họ tự giảm 1 sinh lực cho đến bằng sinh lực của bạn.')
	,('WU025_2','Bác Ngôn','Một lần trong giai đoạn hành động, bạn có thể lựa chọn 1 người chơi khác, lệnh họ bổ sung bài trên tay bằng giới hạn sinh lực của họ. Sau đó ho không thể sử dụng hoặc đánh ra bài trên tay cho đến khi kết thúc lượt này.\n\nTung Hoành: Họ nhận kỹ năng này cho đến khi kết thúc lượt sau của họ, đồng thời kỹ năng này xóa bỏ mô tả "họ bổ sung bài trên tay bằng giới hạn sinh lực của họ".')
	,('WU026_1','Trinh Đặc','Mỗi lượt chơi mỗi người 1 lần, sau khi bạn trở thành mục tiêu người khác dùng bài sắc Đen (Cơ Bản hoặc Cẩm Nang phổ thông), bạn có thể lệnh người dùng bài lượt này chọn 1 mục:\n\t1. Lượt này không thể tiếp tục dùng bài có cùng màu này;\n\t2. Thẻ bài này vô hiệu với bạn.')
	,('WU026_2','Chi Vi','Khi bạn lật mở thẻ tướng bài này, bạn chọn 1 người chơi khác. Sau khi người đó gây sát thương, bạn rút 1 thẻ bài; Sau khi người đó nhận sát thương, bạn bỏ đi ngẫu nhiên 1 thẻ bài trên tay. Bài bỏ trong giai đoạn bỏ bài của bạn người đó đều thu lấy hết. Khi người đó trận vong, nếu bạn đang lất mở cả 2 thẻ tướng, bạn lật úp thẻ tướng này.')
	,('WU027_1','Chiêu Tiết','Sát thương từ thẻ bài sắc Đỏ gây ra cho bạn -1. Khi bạn trở thành mục tiêu của thẻ bài Cẩm Nang có thời gian, hủy bỏ nó.')
	,('WU027_2','Phụng Vũ','Giai đoạn chuẩn bị của người khác cùng thế lực với bạn, bạn có thể giao cho họ 1 thẻ bài, sau đó dựa trên tình trạng hiện tại của họ để lần lượt chấp hành các mục sau:\n\t- Bài trên tay ít nhất, bạn có thể lệnh cho họ hồi phục 1 sinh lực.\n\t- Sinh lực đang ít nhất, bạn có thể lệnh họ »Kiểm Sách« và thu 1 thẻ Cơ Bản.')
	,('WU028_1','Nghịch Trảm','Khi lượt 1 người khác kết thúc, nếu lượt này của bạn mất đi thẻ bài cuối cùng trên tay, hoặc bạn có sử dụng qua bài để triệt tiêu thẻ bài của họ, bạn có thể lệnh họ chọn 1 mục:\n\t1. Bạn thu lấy 1 thẻ bài của họ.\n\t2. Bạn xem như sử dụng thẻ [Sát] xuyên phòng cụ lên họ.')
	,('WU029_1','Thân Trọng','Bắt đầu lượt chơi của bạn, bạn có thể tiến hành hoán đổi Phó Tướng với 1 người khác cùng thế lực.')
	,('WU029_2','Chiêu Phụ','Bắt đầu giai đoạn hành động, nếu trên bàn chơi không có nhiều hơn 2 "Thưởng", bạn có thể bỏ 1 thẻ và lệnh 1 người khác nhận tiêu ký "Thưởng". Sau khi người có "Thưởng" kết toán xong thẻ Cơ Bản/Cẩm Nang phổ thông họ sử dụng, nếu bạn phù hợp với điều kiện sử dụng thẻ đó (bỏ qua thời điểm sử dụng), bạn có thể bỏ đi 1 "Thưởng", xem như bạn sử dụng thẻ đó.')
	,('WU030_1','Hoãn Thích','Trước khi thẻ phán xét của người cùng thế lực với bạn có hiệu lực, bạn có thể đánh ra 1 thẻ để thay đổi thẻ phán xét đó.')
	,('WU030_2','Hoằng Viện','Khi bạn rút thêm bài bởi hiệu ứng "Hợp/Liên", bạn có thể lệnh 1 người cùng thế lực rút bài thay bạn. Một lần trong giai đoạn hành động, bạn có thể lệnh 1 thẻ bài phổ thông xem như hiệu ứng "Hợp/Liên".')
	,('WU030_3','Minh Triết','Ngoài lượt của bạn, khi bạn đánh ra thẻ bài sắc Đỏ hoặc mất đi thẻ bài sắc Đỏ trong vùng Trang Bị, bạn có thể rút 1 thẻ.')
	,('WU031_1','Lương Chấp','Một lần mỗi lượt đối với mỗi người, bạn sử dụng thẻ bài Cơ Bản hoặc Cẩm Nang phổ thông, bạn có thể chỉ định thêm 1 người chơi có tướng úp trở thành mục tiêu của thẻ này. Sau khi kết toán, họ có thể mở 1 thẻ tướng và chọn:\n\t1. Bỏ 1 thẻ, xem như sử dụng [Sát] lên bạn;\n\t2. Rút 1 thẻ, xem như sử dụng [Đào] lên bạn.')
	,('WU031_2','Từ Chính','Một lần mỗi vòng, đầu giai đoạn hành động của người khác, nếu sinh lực họ ≤ bạn, bạn có thể bỏ 1 thẻ và tiến hành Hiến Sách cho họ. Giai đoạn hành động của họ, họ chọn:\n\t1. Cưỡng chế 1 người khác không cùng thế lực với họ chấp hành 1 [Quân Lệnh];\n\t2. Bỏ [Quân Lệnh] này và bỏ qua giai đoạn bỏ bài của lượt này.')
	,('WU032_1','Mục Mục','Khi bạn trở thành mục tiêu của thẻ bài đa mục tiêu, nếu bạn:\n\t1. Liền kề với người sử dụng, bạn có thể bỏ 1 thẻ của họ hoặc lệnh họ rút 1 thẻ. \n\t2. Không liền kề với người sử dụng, bạn có thể bỏ 1 thẻ, hủy bỏ 1 mục tiêu của thẻ này.')
	,('WU032_2','Biệt Bão','Sau khi bạn mất bài trong vùng trang bị, bạn có thể cùng với 1 người cùng thế lực hoán đổi Phó Tướng, sau đó bạn có thể lệnh 1 người chơi có sinh lực thấp nhất trên bàn chơi hồi phục 1 sinh lực.')
	,('WU033_1','Hổ Huân','Khi 1 người kết thúc lượt, nếu trong lượt này có người rơi vào trạng thái hấp hối do bạn gây sát thương, bạn chọn 1 mục:\n\t1. Nếu bạn không phải người duy nhất có giới hạn sinh lực cao nhất, bạn tăng 1 giới hạn sinh lực và hồi 1 sinh lực;\n\t2. Bạn có thể di chuyển 1 thẻ trong vùng trang bị/phán xét của 1 người sang vùng chơi của người khác.')
	,('WU033_2','Nguyên Tòng','Kết thúc giai đoạn hành động của 1 người cùng thế lực, nếu trong giai đoạn này họ không gây ra sát thương, họ có thể giao cho bạn 1 thẻ, sau đó bạn có thể sử dụng 1 thẻ trên tay.')
	,('WU034_1','Lễ Phụ','Giai đoạn hành động giới hạn 1 lần, bạn có thể chọn 1 người, lệnh họ bỏ 2 thẻ, sau đó bạn xem thẻ trên đầu chồng bài rút và giao cho họ.')
	,('WU034_2','Ngôn Trung','Bắt đầu giai đoạn kết thúc, bạn có thể chọn 1 chất và chọn 1 người khác có bài trên tay, bạn bỏ 1 thẻ trên tay của họ. Nếu chất thẻ đã bỏ và chất đã đoán khác chất, bạn bỏ 1 thẻ. Nếu cùng chất và chất đó là\n\t♥️: bạn hồi 1 sinh lực;\n\t♦️: bạn rút 1 thẻ, thoát trạng thái liên hoàn;\n\t♣️: họ giao bạn 1 thẻ;\n\t♠️: họ tự giảm 1 sinh lực.')
	,('WU035_1','Uỷ Dị','Giai đoạn hành động của người cùng thế lực giới hạn 1 lần, khi họ sử dụng thẻ Phi trang bị thứ 2 trong giai đoạn này chỉ định cùng 1 mục tiêu, họ có thể bỏ 1 thẻ Phi cơ bản sau đó »Hiến Sách« đối với bạn. Giai đoạn chuẩn bị, với mỗi [Quân Lệnh] bạn có, bạn theo thứ tự chọn 1 mục:\n\t1. Lệnh 1 người khác chấp hành [Quân Lệnh] này;\n\t2. Bạn bỏ đi [Quân Lệnh] này, sau đó bỏ 1 thẻ của 1 người khác.')
	,('WU035_2','Loạn Vi','Khi 1 người thu lấy [Quân Lệnh] từ chồng [Quân Lệnh] không phải theo cách này, bạn có thể thu lấy ngẫu nhiên 1 thẻ [Quân Lệnh]. sau đó bạn chọn 1 mục:\n\t1. Bạn thay đổi [Quân Lệnh] này của bạn với 1 [Quân Lệnh] trong đó;\n\t2. Bạn bỏ đi [Quân Lệnh] này sau đó rút 1 thẻ.')
	,('WU036_1','Diệu Sang','Một lần trong giai đoạn hành động, bạn có thể dùng 1 thẻ Phi Cơ Bản như [Vô Trung Sinh Hữu]')
	,('WU036_2','Liên Hoa','Mỗi lượt người chơi, sau khi bạn kết toán xong thẻ [Sát] chỉ định bạn làm mục tiêu. Bạn có thể chọn 1 người cùng thế lực chưa rút bài từ kỹ năng này, bạn lệnh họ rút 1 thẻ bài')
	,('WU037_1','Điều Quy','Giai đoạn hành động hạn 1 lần, bạn có thể bỏ 1 thẻ bài Trang Bị, lệnh tối đâ 2 người khác rời khỏi bàn chơi hết lượt này, nếu vì vậy mà thế lực bạn có sự thay đổi đồng thời tạo thành quan hệ đội hình, bạn rút X thẻ bài (X là số người trong đội hình).')
	,('WU037_2','Phong Dương','Người chơi không cùng thế lực hoặc người chơi Ẩn Danh không thể bỏ đi hoặc thu lấy bài trong vùng Trang Bị của người chơi có quan hệ đội hình với bạn.')
	,('WU038_1','Ngạo Tài','Ngoài lượt của bạn, khi bạn cần sử dụng/đánh ra 1 thẻ Cơ Bản, bạn xó thể xem 2 thẻ trên đầu chồng bài rút, sau đó nếu trong đó có thẻ bài bạn cần, bạn có thể sử dụng/đánh ra 1 thẻ đó.')
	,('WU038_2','Khóa Võ','Giai đoạn hành động, bạn có thể chọn 1 [Quân Lệnh], lệnh tất cả người chơi không cùng hoặc Ẩn Danh trong phạm vi công kích của bạn, lệnh họ thực hiện [Quân Lệnh] này, nếu người đó không chấp hành thì bạn gây 1 sát thương cho họ, sau đó bạn rút 1 thẻ bài; Sau khi tất cả người chơi kết toán [Quân Lệnh] xong, nếu trong lúc kết toán [Quân Lệnh] có người thoát trạng thái hấp hối thì bạn tự giảm 1 sinh lực.')
	,('WU039_1','Dẫn Binh','Giai đoạn kết thúc, bạn có thể đặt tùy ý bài Phi Cơ Bản lên thẻ tướng này, gọi là "Trách"; Sau khi bạn nhận sát thương bởi [Quyết Đấu] hoặc [Sát]. Bạn buộc di trừ 1 thẻ "Trách"')
	,('WU039_2','Tuyệt Địa','Giai đoạn chuẩn bị, bạn lựa chọn 1 mục:\n\t1. Lệnh 1 người khác có sinh lực ≤ bạn thu lấy hết số thẻ "Trách", sau đó họ hồi phục 1 sinh lực và rút số thẻ tương tự.2. Bỏ hết thẻ "Trách", bạn bổ sung bài bằng giới hạn sinh lực.')
	,('WU040_1','Tích Tú','1. Mỗi lượt giới hạn 1 lần, sau khi bạn trở thành mục tiêu của thẻ bài của người khác, nếu trong vùng trang bị có thẻ cùng chất với thẻ mà họ sử dụng, bạn rút 1 thẻ\n\t2. Khi người khác bỏ đi thẻ cuối trong vùng trang bị của bạn, bạn ngăn chặn lần mất bài này.')
	,('WU040_2','Trần Kiến','Giai đoạn chuẩn bị, bạn có thể lật mở X thẻ trên đầu chồng bài rút (X là số bài trong vùng trang bị của bạn, ít nhất là 1). Sau đó bạn lựa chọn 1 mục:\n\t1. Bạn bỏ 1 thẻ, lệnh 1 người thu lấy 1 thẻ cùng chất trong đó\n\t2. Bạn sử dụng 1 thẻ trong đó. Sau kết toán, các thẻ còn lại tiến nhập chồng bài bỏ.')
	,('WU041_1','Kiếp Giang','Giai đoạn hành động 1 lần, bạn có thể di trừ 1 thẻ trong vùng trang bị của 1 người chơi đến vùng trang bị tương ứng đang trống của 1 người khác đang ngồi liền kề họ hoặc 1 người chơi đang trong trạng thái liên hoàn. Sau đó, người mất đi trang bị có thể sử dụng 1 thẻ [Sát], nếu thẻ đó gây sát thương lên bạn, họ thu hồi trang bị đó lên tay.')
	,('WU041_2','Đảm Nghênh','Khi thẻ tướng Ngô của 1 người chơi rời khỏi bàn chơi, nếu thẻ tướng này không phải là tướng bị di trừ, bạn có thể thể di trừ thẻ tướng này và thay thế bằng tướng Ngô bị di trừ đó, sau đó bạn thu được 1 tiêu ký "Tiên Phong".')
	,('WU042_1','Huyền Chú','Giai đoạn hành động 1 lần, nếu trên tướng bạn không có "Chú", bạn có thể chọn 1 người khác, bạn thu lấy 1 thẻ của họ và đặt lên tướng, gọi là thẻ "Chú", sau đó bạn xem như sử dụng 1 thẻ cơ bản tùy ý (tính vào giới hạn sử dụng trong lượt), sau kết toán bạn tự bỏ 2 thẻ bài.')
	,('WU042_2','Kiển Ngạc','Nếu trên tướng bạn có "Chú", thẻ bài cùng chất với "Chú" do bạn sử dụng trong lượt của bạn không thể hưởng ứng; Sau khi người cùng thế lực nhận sát thương, bạn lệnh họ thu lấy thẻ "Chú".')
	,('WU043_1','Tháo Lịch','Sau khi bạn lần đầu lật mở hết các thẻ tướng, bạn thu lấy 1 kỹ năng bạn chưa sở hữu: 『Anh Hồn』, 『Kích Ngang』. Khi bạn trận vong, bạn có thể lệnh 1 người chơi thu lấy kỹ năng 『Kích Tiếu』.')
	,('WU043_2','Kích Tiếu','Giai đoạn hành động 1 lần, bạn có thể đấu điểm với 1 người khác, người thắng gây 1 điểm sát thương lên người không thắng, sau đó bạn có thể thu lấy thẻ đấu điểm lên tay, nếu bạn làm thế, bạn đem 1 thẻ trên tay đặt dưới đáy chồng bài rút.')
	,('WU044_1','Vấn Quái','Giai đoạn chuẩn bị của người cùng thế lực, họ có thể đặt 1 thẻ của họ xuống đáy chồng bài rút, sau đó họ tiến hành lật phán xét, nếu thẻ phán xét là:\n\t1. Sắc đen, họ thu lấy thẻ phán xét đó;\n\t2. Sắc đỏ, bạn và họ rút 1 thẻ.')
	,('WU044_2','Phục Chu','Giai đoạn kết thúc của người khác không cùng thế lực,  nếu trong lượt này họ có có gây sát thương, bạn có thể lật mở X thẻ từ đáy chồng bài rút đồng thời sử dụng tất cả thẻ [Sát] được mở ra lên họ, các thẻ không phải [Sát] tiến vào chồng bài bỏ (X là số sát thương họ gây ra trong lượt, tối đa là 3).')
	,('WU045_1','Tụng Thục','Giai đoạn hành động giới hạn 1 lần, bạn có thể đấu điểm cùng 1 người chơi khác, nếu bạn không thắng, bạn xem như sử dụng 1 thẻ [Vô Trung Sinh Hữu], nếu bạn thắng, bạn có thể giao 1 thẻ cho 1 người khác.')
	,('WU045_2','Tư Biện','Giai đoạn rút bài, bạn có thể bỏ qua rút bài, sửa thành lật mở 4 thẻ trên đỉnh chồng bài, sau đó thu lấy 2 thẻ (1 thẻ điểm cao nhất và 1 thẻ điểm thấp nhất), sau đó bạn có thể giao 1 thẻ trong số thẻ dư cho 1 người khác, thẻ còn lại đưa vào chồng bài bỏ.')
	,('WU046_1','Đoạn Phát','Giai đoạn hành động, bạn có thể bỏ đi tùy ý thẻ sắc Đen, sau đó bạn rút lại lượng thẻ bài tương đương (tổng số lượng bài bạn bỏ đi theo cách này trong giai đoạn này ≤ giới hạn sinh lực của bạn).')
	,('WU046_2','Dụ Địch','Giai đoạn kết thúc, bạn có thể lệnh 1 người khác bỏ đi 1 thẻ bài trên tay của bạn. Nếu thẻ bài bỏ đi không phải sắc Đen, bạn rút 1 thẻ; nếu thẻ bài bỏ đi không phải là [Sát], bạn thu lấy 1 thẻ của họ.')
	,('WU047_1','Oán Ngữ','Giai đoạn hành động 1 lần, bạn có thể rút 1 thẻ, sau đó đặt 1 thẻ trên tay lên thẻ tướng này, gọi là thẻ "Oán".')
	,('WU047_2','Tịch Nhân','Sau khi bạn nhận sát thương, nếu thẻ gây sát thương có cùng màu với 1 trong các thẻ "Oán" (đang đặt trên lá bài tướng), bạn lệnh nguồn gây sát thương phải tiến hành lựa chọn:\n\t1. Giới hạn trữ bài lượt này -4\n\t2. Không được sử dụng bài Cơ Bản trong lượt này.')
	,('WU048_1','Đồng Lễ','Mỗi lượt 1 lần, khi bạn sử dụng 1 thẻ bài chỉ định mục tiêu, bạn có thể lật mở tất cả bài trên tay. Nếu tất cả đều cùng màu, thẻ bài đó được kết toán thêm 1 lần nữa.')
	,('WU048_2','Xa Táng','Khi bạn lần đầu rơi vào trạng thái hấp hối, bạn có thể rút 4 thẻ bài.')
	,('WU049_1','Đảm Thủ','Một lần trong mỗi vòng chơi, giai đoạn chuẩn bị của 1 người, bạn có thể bỏ tất cả thẻ trong vùng chơi của bạn (tối thiểu là 1). Bắt đầu mỗi giai đoạn trong lượt đó, trừ giai đoạn chuẩn bị và kết thúc, nếu bài trên tay bạn ≤ X (X là số bài bạn bỏ bằng cách này), bạn chọn:\n\t1. Rút 1 thẻ\n\t2. Lệnh cho số thẻ rút từ 『Đảm Thủ』trong lượt này +1 Sau khi bạn rút ít nhất 4 thẻ cùng lúc bởi kỹ năng 『Đảm Thủ』, bạn có thể gây 1 sát thương có người đang có lượt.')
	,('WU050_1','Cự Thiên','Mỗi lượt 1 người chơi, mỗi mục giới hạn 1 lần, sau khi bạn gây sát thương cho 1 người khác, bạn có thể chọn 1 mục:\n\t1. Lệnh 1 người cùng thế lực với người nhận sát thương bỏ X thẻ trên tay (X là số thẻ họ vượt quá số sinh lực hiện tại, tối đa là 5)\n\t2. Lệnh 1 người cùng thế lực với bạn bổ sung bài bằng giới hạn sinh lực của họ.')
	,('QUN001_1','Lôi Kích','Khi bạn sử dụng hay đánh ra thẻ [Né], bạn có thể lệnh cho 1 người chơi khác tiến hành phán xét, nếu kết quả là ♠️, bạn gây ra 2 sát thương hệ Lôi lên họ.')
	,('QUN001_2','Quỷ Đạo','Trước khi thẻ phán xét của 1 người chơi có hiệu lực, bạn có thể đánh ra 1 thẻ bài sắc Đen để hoán đổi với thẻ phán xét đó.')
	,('QUN002_1','Loạn Kích','Giai đoạn hành động, bạn có thể sử dụng 2 thẻ trên tay như [Vạn Tiễn Tề Phát] (không thể sử dụng với những thẻ có chất mà bạn đã dùng theo cách này trong lượt này). Khi người chơi cùng thế lực đánh lá [Né] để hưởng ứng thẻ này, sau khi kết toán, họ có thể rút 1 thẻ bài.')
	,('QUN003_1','Loạn Vũ','Giai đoạn hành động, bạn lệnh cho tất cả người khác sử dụng thẻ [Sát] đối với mục tiêu là người chơi được tính khoảng cách nhỏ nhất, nếu không họ phải tự mất 1 sinh lực.')
	,('QUN003_2','Hoàn Sát','Trong lượt của bạn, khi có người chơi rơi vào trạng thái hấp hối, chỉ bạn và người đó mới có thể dùng [Đào].')
	,('QUN003_3','Duy Mạc','Khi bạn trở thành mục tiêu của Câm Nang sắc Đen, hủy bỏ nó.')
	,('QUN004_1','Đoạn Trường','Khi bạn trận vong, bạn lệnh người chơi giết bạn mất đi 1 kỹ năng tướng do bạn chọn.')
	,('QUN004_2','Bi Ca','Sau khi 1 người chơi nhận sát thương từ thẻ [Sát], bạn có thể bỏ đi 1 thẻ và lệnh người chơi chịu sát thương tiến hành phán xét:\n\t♥️: người đó hồi phục 1 sinh lực\n\t♦️: người đó rút 2 thẻ bài\n\t♣️: nguồn gây sát thương bỏ đi 2 thẻ bài\n\t♠️: nguồn gây sát thương đặt chồng tướng')
	,('QUN005_1','Song Hùng','Giai đoạn rút bài, bạn có thể thay vì rút bài mà tiến hành phát xét. Nếu làm như thế, trong lượt này, bạn có thể sử dụng những thẻ trên tay không cùng màu với thẻ phán xét xem như thẻ [Quyết Đấu]. Ngoài ra, trong lượt của bạn, khi thẻ phán xét của bạn có hiệu lực, bạn có thể thu lấy thẻ phán xét.')
	,('QUN006_2','Kiện Xuất','Sau khi bạn sử dụng thẻ [Sát] chỉ định 1 mục tiêu, bạn có thể bỏ đi 1 thẻ bài của người đó, nếu thẻ bỏ đi là:\n\t- Thẻ Trang Bị, họ không thể dùng [Né].\n\t- Không phải là Trang Bị, họ thu lấy thẻ [Sát] bạn vừa sử dụng.')
	,('QUN007_1','Vô Song','Sau khi bạn sử dụng thẻ [Sát] chỉ định mục tiêu, họ phải sử dụng 2 thẻ [Né] để triệt tiêu thẻ [Sát] của bạn. Người chơi tiến hành [Quyết đấu] với bạn phải sử dụng 2 thẻ [Sát] cho mỗi lần đáp trả. Khi bạn sử dụng [Quyết Đấu] không chuyển hóa có thể chọn thêm tối đa 2 mục tiêu cho thẻ này.')
	,('QUN008_1','Ly Gián','Một lần trong giai đoạn hành động, bạn có bỏ đi 1 thẻ bài và lựa chọn 2 người Nam khác, xem như 1 người sử dụng [Quyết Đấu] lên người còn lại.')
	,('QUN008_2','Bế Nguyệt','Giai đoạn kết thúc, bạn có thể rút 1 thẻ bài.')
	,('QUN009_1','Cấp Cứu','Ngoài lượt của bạn, bạn có thể sử dụng thẻ bài sắc Đỏ xem như [Đào].')
	,('QUN009_2','Trừ Lệ','Một lần trong giai đoạn hành động, nếu bạn có bài, bạn có thể chọn tối đa 3 người khác có bài và không cùng thế lực với nhau hoặc không có thế lực, sau đó bạn bỏ đi 1 thẻ bài của bạn và họ. Nếu theo cách này, khi kết toán kết thúc, người mất đi thẻ chất ♠️ được rút 1 thẻ.')
	,('QUN010_1','Danh Sĩ','Khi bạn nhận sát thương, nếu nguồn sát thương có võ tướng chưa lật mở, sát thương này -1.')
	,('QUN010_2','Lễ Nhượng','Khi thẻ bài của bạn bị bỏ đi và tiến nhập vào chồng bài bỏ, bạn có thể đem thẻ bài đó giao cho 1 người chơi khác.')
	,('QUN011_1','Song Nhận','Bắt đầu giai đoạn hành động, bạn có thể đấu điểm với 1 người khác. Nếu bạn thắng, xem như bạn sử dụng 1 thẻ [Sát] lên họ hoặc 1 người chơi khác cùng thế lực với họ (thẻ [Sát] này không tính vào giới hạn [Sát] trong lượt). Nếu không thắng, lượt này bạn không thể sử dụng bài lên người khác.')
	,('QUN012_1','Cuồng Phủ','Sau khi bạn sử dụng thẻ [Sát] gây sát thương cho 1 mục tiêu, bạn có thể bỏ đi hoặc thu lấy 1 thẻ bài của họ.')
	,('QUN013_2','Hùng Dị','Giai đoạn hành động, bạn có thể lệnh cho tất cả người chơi cùng thế lực lần lượt rút 3 thẻ bài. Sau đó, nếu thế lực của bạn là một trong những thế lực ít người chơi nhất, bạn hồi phục 1 sinh lực')
	,('QUN014_1','Họa Thủy','Trong lượt của bạn, người chơi khác không thể lật mở thẻ tướng. Người chơi không cùng thế lực đang có thẻ tướng đang úp không thể dùng hoặc đánh ra thẻ [Né] để hưởng ứng bài của bạn.')
	,('QUN014_2','Khuynh Thành','Giai đoạn hành động, bạn có thể bỏ đi 1 thẻ sắc Đen và chọn 1 người khác đang mở tất cả tướng, sau đó bạn úp 1 thẻ tướng, sau đó bạn úp 1 thẻ tướng của họ. Nếu thẻ bài bạn bỏ là thẻ Trang Bị, bạn có thể lựa chọn 1 người chơi khác và lặp lại thao tác này.')
	,('QUN015_1','Tùy Thế','Khi người chơi khác tiến vào trạng thái hấp hối, nếu nguồn sát thương là người cùng thế lực, bạn rút 1 thẻ. Khi 1 người chơi khác cùng thế lực trận vong, bạn chọn tự giảm 1 sinh lực hoặc bỏ hết bài trên tay.')
	,('QUN015_2','Tử Gián','Khi bạn mất đi 1 thẻ bài cuối cùng trên tay, bạn có thể bỏ đi 1 thẻ bài của 1 người chơi khác.')
	,('QUN016_1','Viễn Vực','Khi bạn nhận sát thương, nếu bạn không nằm trong phạm vi công kích của nguồn gây sát thương, sát thương bạn nhận -1.')
	,('QUN016_2','Quỷ Thuật','Giai đoạn hành động, với mỗi danh bài giới hạn 1 lần, bạn có thể đem 1 thẻ chất ♠️ trên tay xem như thẻ [Viễn Giao Cận Công] hoặc [Tri Kỷ Tri Bỉ] để sử dụng.')
	,('QUN017_1','Thiên Huyễn','Sau khi 1 người cùng thế lực nhận sát thương, bạn có thể đem 1 thẻ bài của bạn lên trên thẻ võ tướng này, gọi là thẻ "Huyễn", nếu "Huyễn" đó trùng chất với thẻ trước đó thì bỏ nó đi. Khi bạn hoặc người cùng thế lực trở thành mục tiêu duy nhất của thẻ bài Cơ Bản hoặc Cẩm Nang, bạn có thể đem 1 thẻ "Huyễn" đặt vào chồng bài bỏ, hủy bỏ nó.')
	,('QUN018_1','Độc Tửu','Bắt đầu giai đoạn hành động của người chơi, bạn có thể bỏ đi 1 thẻ bài trên tay, xem như họ đã dùng 1 thẻ [Tửu]. Nếu người chơi đó không phải là bạn, họ nhận 1 sát thương từ bạn.')
	,('QUN018_2','Thích Loạn','Khi kết thúc lượt của 1 người chơi, bạn có thể rút 3X + Y thẻ (X là số người chơi mà bạn giết trong lượt này, Y là số người chơi bị người chơi khác giết trong lượt này).')
	,('QUN019_1','Hoành Chinh','Bắt đầu giai đoạn rút bài, nếu sinh lực hiện tại của bạn là 1 hoặc không có bài trên tay, bạn có thể thay vì rút bài đổi thành thu lấy 1 thẻ bài trong vùng chơi của tất cả người khác.')
	,('QUN019_2','Bạo Lăng','Kết thúc giai đoạn hành động, nếu bạn có Phó Tướng, loại bỏ nó. Sau đó giới hạn sinh lực tăng lên 3, hồi phục 3 sinh lực, và nhận kỹ năng 『Băng Hoại』.')
	,('QUN019_3','Băng Hoại','Giai đoạn kết thúc, nếu bạn không là người chơi có sinh lực thấp nhất, bạn chọn chấp hành cả 2 mục bạn thực hiện thêm 1 giai đoạn rút bài.')
	,('QUN020_1','Xuyên Tâm','Giai đoạn hành động, khi bạn dùng [Sát]/[Quyết Đấu] tạo thành sát thương cho mục tiêu không cùng thế lực, nếu họ có Phó Tướng, bạn có thể chặn sát thương lại, và lệnh họ chọn 1 mục:\n\t1. Bỏ tất cả Trang Bị ở vùng Trang Bị (ít nhất 1 thẻ) và tự mất 1 sinh lực.\n\t2. Loại bỏ Phó Tướng.')
	,('QUN020_2','Phong Thỉ','Nếu trong trạng thái "Vây Công" sau khi bạn hoặc người vây công sử dụng thẻ [Sát] chỉ định mục tiêu bị vây công, bạn lệnh họ bỏ đi 1 thẻ Trang Bị trong khu vực Trang Bị.')
	,('QUN021_1','Hung Toán','Giai đoạn hành động, bạn có thể bỏ 1 thẻ trên tay và chọn 1 người cùng thế lực. Bạn gây 1 sát thương cho họ, sau đó bạn rút 3 thẻ và lực chọn "Hạn Định Kỹ" đã phát động của họ. Trước khi kết thúc lượt này, bạn lệnh hồi lại kỹ năng đó xem như chưa được phát động.')
	,('QUN022_1','Dịch Quỷ','Lần đầu khi bạn lật mở tướng này, bạn có thể đem 2 thẻ tướng từ chồng tướng đặt úp lên võ tướng này, gọi là "Hồn". Mỗi lượt mỗi loại giới hạn 1 lần, bạn có thể di trừ 1 thẻ "Hồn", xem như sử dụng 1 thẻ Cơ Bản hoặc Cẩm Nang phổ thông (trừ thẻ [Né] và [Vô Giải], mục tiêu không thể bao gồm các người chơi thuộc thế lực khác với thế lực "Hồn" trừ Dã Tâm Gia).')
	,('QUN022_2','Cấp Hồn','Sau khi bạn nhận sát thương, hoặc sau khi người không cùng thế lực với bạn thoát khỏi trạng thái hấp hối, bạn có thể đem 1 thẻ tướng từ chồng tướng đặt úp lên võ tướng, nhập vào "Hồn". Giai đoạn chuẩn bị, bạn có thể di trừ tối đa 2 thẻ "Hồn" và rút "Hồn" tương đương.')
	,('QUN023_1','Tòng Gián','Khi bạn gây sát thương ngoài lượt hoặc bạn nhận sát thương trong lượt của bạn, sát thương này +1.')
	,('QUN023_2','Phụ Địch','Sau khi nhận sát thương, bạn có thể đem 1 thẻ bài trên tay giao cho nguồn sát thương. Nếu làm vậy, bạn gây 1 sát thương cho người cùng thế lực với họ với điều kiện họ có sinh lực hiện tại nhiều nhất và không thấp hơn bạn.')
	,('QUN024_1','Dong Tứ','Nếu trên bàn chơi không có 1 Đại Thế Lực duy nhất, thế lực của bạn xem như Đại Thế Lực duy nhất. Giai đoạn rút bài, bạn rút thêm 1 thẻ; đầu giai đoạn hành động, bạn xem như sử dụng [Tri Bỉ Tri Kỷ]. Khi bạn là mục tiêu của [Tri Bỉ Tri Kỷ] bạn mở ra tất cả bài trên tay.')
	,('QUN024_2','Ngụy Đế','Giai đoạn hành động 1 lần, bạn có thể phát động 1 [Quân Lệnh] lên người khác có rút bài trong lượt này. Nếu họ không chấp hành, bạn thu lấy tất cả thẻ bài trên tay của họ, sau đó bạn đem thẻ trên tay giao lại lượng bài tương đương cho họ.')
	,('QUN025_1','Cuồng Tài','Trong lượt của bạn, bạn sử dụng bài không hạn chế khoảng cách và số lần; Bắt đầu giai đoạn bỏ bài, nếu bạn trong lượt này có dùng bài nhưng không gây ra sát thương, giới hạn trữ bài của bạn tỏng lượt này -1. Nếu bạn không dùng bài trong lượt này, giới hạn trữ bài trong lượt này +1.')
	,('QUN025_2','Thân Kiếm','Sau khi bạn trở thành mục tiêu duy nhất của thẻ bài bở người khác, bạn có thể bỏ tất cả bài trên tay và chọn 1 mục:\n\t1. Bạn gây 1 sát thương lên họ.\n\t2. Bạn bỏ số bài tương đương của họ.')
	,('QUN026_1','Phong Lược','Giai đoạn hành động giới hạn 1 lần, bạn có thể đấu điểm với 1 người khác. Nếu bạn thắng, họ đem 2 thẻ trong vùng chơi giao cho bạn. Nếu bạn không thắng, bạn giao cho họ 1 thẻ bài.\n\nTung Hoành: Họ nhận kỹ năng này cho đến khi kết thúc lượt sau của họ, đồng thời thay đổi mô tả kỹ năng này, hoán đổi "2 thẻ" và "1 thẻ".')
	,('QUN026_2','Ám Dũng','Một lần mỗi lượt, khi người cùng thế lực với bạn với bạn gây sát thương cho 1 người chơi khác. bạn có thể lệnh cho sát thương này tăng lên gấp đôi. Sau đó nếu người chơi đối phương đang:\n\t1. Lật mở 2 tướng; bạn tự giảm 1 sinh lực và mất kỹ năng này.\nt2. Chỉ lật mở một tướng; bạn tự bỏ 2 thẻ trên tay.')
	,('QUN027_1','Truy Đố','Một lần trong giai đoạn hành động, khi bạn gây sát thương cho 1 người chơi, bạn có thể lệnh họ chọn 1 mục:\n\t1. Sát thương này +1.\n\t2. Tự bỏ tất cả bài trong khu Trang Bị (tối thiểu 1). Nếu người đó là Nữ, bạn có thể bỏ 1 thẻ bài, lệnh họ theo thứ tự chấp hành cả 2 mục.')
	,('QUN027_2','Kỳ Cung','Khi bạn rơi vào trạng thái hấp hối ngoài lượt của bạn, bạn có thể di trừ đi Phó Tướng của bạn, sau đó lệnh người đang có lượt chọn 1 mục:\n\t1. Họ nhận 1 kỹ năng không có nhãn loại hình kỹ năng (kỹ năng như Tỏa Định Kỹ, Trận Pháp Kỹ, ...) của tướng di trừ, sau đó bạn hồi sinh lực đến tối đa.\n\t2. Bạn hồi 1 sinh lực.')
	,('QUN028_1','Phạt Nhu','Mỗi lượt 1 người giới hạn 1 lần, khi 1 người khác thoát khỏi trạng thái hấp hối, nếu nguồn sát thương hoặc lượt hiện tại là người cùng thế lực, bạn có thể bỏ đi 1 thẻ trên tay, gây sát thương với người vừa thoát hấp hối.')
	,('QUN028_2','Trung Tiết','Khi thẻ [Hiệp Thiên Tử Dĩ Lệnh Chư Hầu] tiến vào chống bài bỏ ngoài lượt của bạn, bạn có thể chọn tự giảm sinh 1 lực hoặc bỏ đi 1 thẻ chất ♥️ trên tay, sau đó bạn thu lấy thẻ đó. Khi bạn sử dụng [Hiệp Thiên Tử Dĩ Lệnh Chư Hầu], bạn bỏ qua điều kiện Đại Thế Lực và bạn lệnh những người cùng thế lực rút 1 thẻ.')
	,('QUN029_1','Hào Khôi','Bắt đầu giai đoạn hành động, bạn có thể rút 2 thẻ, nếu làm vậy, giai đoạn bỏ bài lượt này: khi có bài tiến vào chồng bài bỏ, bạn đem những thẻ này giao cho 1 người không cùng thế lực. Nếu bạn chưa giao bài theo cách này, bạn có thể lệnh 1 người cùng thế lực thay đổi Phó Tướng 1 lần, và bạn có thể lật úp thẻ tướng này.')
	,('QUN029_2','Hư Thực','Khi người khác sử dụng bài chỉ định bạn là mục tiêu, nếu tướng này đang ở trạng thái úp, bạn có thể lật mở thẻ tướng này, hủy bỏ nó, sau đó bạn có thể bỏ 1 thẻ của họ.')
	,('QUN030_1','Phấn Việt','Giai đoạn hành động giới hạn X lần (X là số lượng thế lực trên bàn chơi), bạn có thể đấu điếm với 1 người. Nếu thắng, bạn chọn 1 mục:\n\t1. Họ không thể sử dụng/đánh ra thẻ bài cho đến khi kế thúc lượt;\n\t2. Xem như bạn sử dụng thẻ [Sát] Lôi lên họ (không tính vào giới hạn dùng [Sát] trong lượt).\nNếu bạn không thắng, bạn không thể phát động kỹ năng này cho đến hết lượt này.')
	,('QUN031_1','Tức Tuy','Một lần mỗi vòng, ứng với mỗi lựa chọn, khi bạn nhận sát thương, bạn có thể lật mở số thẻ trên bàn tùy ý và lệnh nguồn sát thương chọn 1 mục:\n\t1. Chặn sát thương này, sau đó họ đem số thẻ này tùy ý phân phát cho người khác.\n\t2. Mở ra tất cả bài trên tay, sau đó bỏ tất cả bài có cùng chất với thẻ đó, nếu làm vậy, bạn rút 1 thẻ.')
	,('QUN031_2','Thanh Khắc','Khi người khác thu lấy bài của bạn, họ phải để lộ những thẻ này (lật mở). Khi 1 người mất thẻ để lộ cuối cùng trên tay, bạn lệnh họ rút 1 thẻ hoặc bỏ đi 1 thẻ.')
	,('QUN032_1','Đức Giáo','Mỗi lượt 1 lần với mỗi người chơi, khi 1 người chơi nhận sát thương, nếu đây là lần thứ 2 họ nhận sát thương trong cùng 1 lượt hoặc sát thương này > 1, bạn có thể lệnh cho sát thương -1. Nếu làm vậy nguồn của sát thương hồi phục 1 sinh lực.')
	,('QUN032_2','Giới Hư','Sau khi người khác sử dụng thẻ chuyển hóa hoặc bìa ảo, hoặc sau khi 1 người khác hồi phụ sinh lực không thông qua sử dụng bài, bạn có thể lật mở 1 thẻ trên tay họ, sau đó bạn có thể lật mở 1 thẻ trên tay họ, sau đó bạn có thể lật mở 1 thẻ trên tay có cùng chất với thẻ đó, nếu bạn làm vậy, bạn rút 1 thẻ.')
	,('QUN033_1','Tuyệt Sách','Giai đoạn kết thúc, bạn có thể gây 1 sát thương cho người chơi không có bài trên tay.')
	,('QUN033_2','Diệt Kế','Giai đoạn hành động 1 lần, bạn có thể đặt 1 thẻ Cẩm Nang sắc Đen lên đỉnh chồng bìa, sau đó lệnh 1 người khác chấp hành [Quân Lệnh]. Nếu họ không chấp hành, bạn có thể bỏ đi của họ 2 thẻ.')
	,('QUN033_3','Phần Thành','Giai đoạn hành động, bạn lệnh tất cả người khác lần lượt lựa chọn 1 mục:\n\t1. Bỏ tối thiểu X thẻ (X là số bài mà người ngồi trước đó bỏ bằng cách này +1).\n\t2. Nhận 1 sát thương Hỏa từ bạn.')
	,('QUN034_1','Khu Thi','Giai đoạn hành động, sau khi bạn sử dụng [Sát] và [Sát] này đã bị triệt tiêu, bạn tăng 1 giới hạn sử dụng [Sát] và tăng số mục tiêu của [Sát] thêm 1 trong giai đoạn này.')
	,('QUN034_2','Nhạn Hàng','Người cùng đội hình với bạn tính khoảng cách đến những người khác -X (X là số người khác có cùng quan hệ đội hình với bạn)')
	,('QUN034_3','Nghĩa Tòng','Sau khi người khác cùng thế lực với bạn sử dụng [Sát] và bị triệt tiêu, họ có thể lệnh bạn thu lấy thẻ [Sát] đó.')
	,('QUN035_1','Trí Trì','Sau khi bạn nhận sát thương ngoài lượt, trong lượt đó nếu bạn trở thành mục tiêu của thẻ [Sát] hoặc Cẩm Nang, bạn hủy bỏ nó.')
	,('QUN035_2','Minh Sách','Giai đoạn hành động giới hạn 1 lần, bạn có thể bỏ 1 thẻ [Sát] hoặc 1 thẻ Vũ Khí, tiến hành »Hiến Sách« đối với 1 người khác chưa có [Quân Lệnh] đồng thời chỉ định 1 người khác ngoài họ. Bắt đầu lượt người tiếp, người được »Hiến Sách« chọn 1 mục:\n\t1. Lệnh người còn lại chấp hành [Quân Lệnh].\n\t2. Bỏ đi thẻ [Quân Lệnh], rút 2 thẻ.')
	,('QUN036_1','Hoạn Thị','Bạn xem như không có giới tính. Khi bạn sử dụng thẻ [Sát] lên 1 người chơi khác, họ lựa chọn:\n\t1. Bỏ 1 thẻ trên tay.\n\t2. Lệnh bạn rút 1 thẻ.')
	,('QUN036_2','Loạn Chính','Sau khi bạn nhận sát thương đầu tiên trong lượt, nếu sau đó bạn nhận thêm sát thương, bạn có thể bỏ đi 1 thẻ để sát thương bạn nhận -1. Sau khi kết toán sát thương, bạn có thể lệnh 2 người chơi cùng thế lực với nhau tiến hành tráo đổi Phó Tướng với nhau. Nếu bạn không phải người bị tráo đổi, kết toán bạn rút 1 thẻ.')
	,('QUN037_1','Trĩ Đạo','Bắt đầu giai đoạn hành động, bạn chọn 1 người khác, cho đến hết lượt này, khoảng cách giữa bạn và họ là 1 và bạn không thể sử dụng bài chỉ định người khác ngoài bạn và họ làm mục tiêu. Sau đó, sau khi bạn gây sát thương lần đầu tiên trong giai đoạn này lên người đó, bạn thu lấy 1 thẻ bài trong vùng chơi của họ.')
	,('QUN037_2','Ký Súc','Nếu bạn trở thành mục tiêu duy nhất của thẻ bài Cơ Bản sắc Đỏ, thì sau khi kết toán thẻ bài này, người sử dụng thẻ bài này lại thêm 1 lần dùng thẻ cùng tên lên bạn. Khi bạn tại 1 giai đoạn bất kỳ ở lượt này nhận sát thương lần thứ 2, bạn chặn sát thương này lại, sau đó di trừ thẻ tướng này.')
	,('QUN038_1','Tập Xạ','Giai đoạn chuẩn bị của người khác, bạn có thể bỏ 1 thẻ bài trong vùng Trang Bị của bạn, xem như sử dụng 1 thẻ [Sát] không hạn chế khoảng cách lên người đó, nếu người đó có sinh lực < bạn thì thẻ [Sát] này không thể hưởng ứng, sau đó bạn có thể tái phát động kĩ năng này. Sau khi kết thúc lượt người này, nếu theo cách này mà bạn có giết người chơi khác, bạn có thể thay đổi Phó Tướng và tướng đổi lên sẽ ở trạng thái lật úp.')
	,('QUN039_1','Quắc Vũ','Đầu giai đoạn hành động, bạn có thể lật mở tất cả bài trên tay, nếu số loại bài trên tay của bạn lớn hơn:\n\t≥ 1, bạn thu lấy 1 thẻ [Sát] từ chồng bài bỏ; \n\≥ 2, bạn dùng bài không giới hạn khoảng cách; \n\t≥ 3, trong lượt này bạn dùng [Sát] hoặc Cẩm Nang phổ thông, có thể chọn thêm 2 mục tiêu. (giới hạn 1 lần).')
	,('QUN039_2','Trang Nhung','Một lần trong giai đoạn hành động, bạn có thể bỏ 1 thẻ cẩm nang, sau đó nhận 『Vô Song』 cho đến hết lượt.')
	,('QUN039_3','Thần Uy','Giai đoạn rút bài, nếu bạn có sinh lực cao nhất, bạn rút thêm 2 thẻ; giới hạn trữ bài +2.')
	,('QUN040_1','Đặc Dũng','Khi bạn nhận sát thương, nếu bạn chưa phát động "Vinh Võ" đồng thời thẻ gây sát thương không phải sắc Đỏ, bạn rút 1 thẻ; Nếu bạn đã phát động "Vinh Võ" và thẻ gây sát thương không phải sắc Đen, nguồn sát thương rút 1 thẻ.')
	,('QUN040_2','Vinh Võ','Sau khi bạn gây sát thương, bạn có thể lật mở thẻ tướng này, bạn tăng 2 giới hạn sinh lực và hồi phục 2 sinh lực. Nếu bạn làm thế, khi bạn trận vong, những người chơi khác cùng thế lực với bạn mỗi người tự giảm 1 sinh lực.')
	,('QUN041_1','Tập Binh','Kết thúc giai đoạn bỏ bài của 1 người khác, bạn có thể bỏ 1 thẻ trên tay, sau đó thu lấy 1 thẻ trong số bài bị bỏ đi giai đoạn này có điểm số nhỏ hơn thẻ bạn bỏ, sau đó đặt lên tướng này gọi là [Binh] (tối đa 4 thẻ, mỗi thẻ 1 chất); Bạn có thể sử dụng hoặc đánh ra thẻ [Binh] như bài trên tay.')
	,('QUN041_2','Vãng Kinh','Khi bạn sử dụng hoặc đánh ra thẻ [Binh], nếu bạn không phải là người có sinh lực lớn nhất bàn chơi, bạn rút 1 thẻ.')
	,('QUN042_1','Hãn Dũng','Giai đoạn hành động giới hạn 1 lần, bạn có thể bỏ X thẻ trên tay đồng thời chỉ định X người khác trên bàn chơi trở thành mục tiêu (mỗi thế lực chỉ định được tối đa 1 người), xem như bạn sử dụng [Nam Man Nhập Xâm] lên tất cả các mục tiêu đó, nếu thẻ này gây ra sát thương, bạn có thể giao 1 thẻ cho 1 người khác, chọn rút 3 thẻ hoặc hồi phục 1 sinh lực.')
	,('QUN042_2','Nhiên Thương','\t1. Nếu bạn chưa trang bị [Đằng Giáp], sát thương từ hệ Hỏa bạn nhận +1\n\t2. Khi bạn nhận sát thương từ [Nam Man Nhập Xâm], sát thương bạn nhận -1\n\t3. Sau khi thẻ [Đằng Giáp] rời khỏi vùng chơi của người khác và tiến vào chồng bài bỏ, bạn thu nó lên tay và rút 2 thẻ.')
	,('QUN043_1','Tập Chúng','Sau khi bạn hưởng ứng thẻ bài từ người chơi khác, nếu trên tướng này chưa có "Chúng", bạn có thể đặt 1 thẻ Phi Cẩm Nang sắc Đen lên thẻ tướng này, gọi là "Chúng", sau đó bạn rút 2 thẻ.')
	,('QUN043_2','Hoặc Chúng','Giai đoạn hành động 1 lần, bạn có thể bỏ đi 1 thẻ, đem 1 thẻ "Chúng" sử dụng như [Binh Lương Thốn Đoạn] không giới hạn khoảng cách.')
	,('QUN043_3','Nhật Tuệ','Bạn sử dụng bài lên người khác có thẻ trong vùng phán xét không giới hạn khoảng cách; Sau khi bạn gây sát thương từ thẻ [Sát], lệnh 1 người khác có bài trong vùng phán xét rút 1 thẻ.')
	,('QUN044_1','Thiên Tắc','Khi 1 người cùng thế lực với bạn gây ra sát thương thứ 2 trong lượt của họ, bạn có thế lệnh họ tiến hành phán xét (nếu là bạn thì bỏ qua bước thực hiện phán xét). Nếu thẻ phán xét là sắc Đen, bạn lệnh cho sát thương này mang thuộc tính Lôi.')
	,('QUN044_2','Địa Pháp','\t1. Mỗi lượt 1 lần, sau khi 1 người chơi nhận sát thương hệ Lôi, bạn rút 1 thẻ\n\t2. Sau khi thẻ phán xét sắc Đen tiến vào chồng bài bỏ, bạn có thể bỏ 1 thẻ và rút 1 thẻ.')
	,('QUN045_1','Liên Tru','Giai đoạn hành động giới hạn 1 lần, bạn có thể lật mở và giao 1 thẻ trên tay cho 1 người khác, nếu thẻ bài bạn giao là:\n\t1. sắc Đen, bạn xem như sử dụng [Quá Hà Sách Kiều] lên tất cả người cùng thể lực có quan hệ đội hình liền kề với người đó\n\t2. sắc Đỏ, bạn xem như sử dụng [Hỏa Công] lên tất cả người cùng thể lực có quan hệ đội hình liền kề với người đó, sau khi kết toán có gây sát thương bạn rút 2 thẻ.')
	,('QUN045_2','Thị Sủng','\t1. Dương: sau khi bạn bị chỉ định thành mục tiêu duy nhất bởi thẻ bài do người khác sử dụng, bạn có thể lệnh họ giao cho bạn 1 thẻ do người khác sử dụng, bạn có thể lệnh họ giao bạn 1 thẻ trên tay\n\t2. Âm: sau khi bạn sử dụng bài chỉ định 1 mục tiêu duy nhất, bạn có thể thu của họ 1 thẻ.')
	,('QUN046_1','Phục Kỵ','Khi bạn sử thẻ [Sát], Cẩm Nang phổ thông hoặc Cẩm Nang thế lực, bạn lệnh cho tất cả người chơi nằm trong khoảng cách 1 của bạn không thể hưởng ứng lại thẻ bài này.')
	,('QUN046_2','Kiêu Tự','Khi bạn gây ra sát thương hoặc nhận vào sát thương, nếu bạn là người duy nhất có nhiều bài trên tay nhất, lệnh sát thương ấy +1.')
	,('QUN047_1','Tuấn Công','Giai đoạn hành động 1 lần, bạn có thể tự giảm 1 sinh lực hoặc tự bỏ đi 1 thẻ, xem như sử dụng 1 thẻ [Sát] không giới hạn khoảng cách. (thẻ [Sát] bằng cách này không tính vào giới hạn trong lượt).')
	,('QUN047_2','Đẳng Lạc','Sau khi bạn sử dụng thẻ [Sát] chỉ định 1 mục tiêu duy nhất, hoặc sau khi bạn trở thành mục tiêu của thẻ [Sát] từ người khác, nếu sinh lực của bạn và họ bằng nhau, bạn có thể rút 1 thẻ.')
	,('QUN048_1','Tri Lược','Giai đoạn hành động 1 lần, bạn có thể tự giảm 1 điểm sinh lực và lệnh giới hạn trữ bài của bạn trong lượt này +1, sau đó chọn một trong hai lựa chọn:\n\t1. Rút 1 thẻ bài, xem như sử dụng 1 thẻ [Sát] không giới hạn khoảng cách. (thẻ [Sát] này không bị tính vào giới hạn trong lượt)\n\t2. Di chuyển 1 thẻ trên bàn chơi')
	,('QUN049_1','Tấn Tích','Khi người chơi khác lật mở thẻ tướng ngoài lượt của họ, bạn có thể xem như sử dụng 1 thẻ [Sát] (không bị giới hạn) lên họ.')
	,('QUN049_2','Hoàn Giáp','Mỗi lượt 1 người 1 lần, sau khi bạn trở thành mục tiêu của thẻ [Sát], nếu bạn chưa trang bị Phòng Cụ, lượt này bạn xem như đang trang bị phòng cụ của người sử dụng, cho đến khi vùng trang bị bạn có phòng cụ. Sau khi bạn chỉ định mục tiêu duy nhất cho [Sát], nếu bạn chưa trang bị Vũ Khí, lượt này của bạn xem như trang bị vũ khí của họ, cho đến khi vùng trang bị bạn có vũ khí.')
	,('QUN050_1','Hoá Thân','Giai đoạn chuẩn bị, bạn có thể thu lấy thẻ "Hoá Thân" (chọn tối đa hai thẻ từ năm thẻ tướng từ chồng tướng dư; nếu bạn đã có bài "Hoá Thân", thì sửa thành di trừ 1 thẻ cũ và thu một thẻ mới). Bạn có thể di trừ một thẻ "Hoá Thân" và phát động 1 kỹ năng không có nhãn kĩ năng của thẻ đó.')
	,('QUN050_2','Tân Sinh','Sau khi bạn nhận sát thương, bạn có thể thu từ chồng tướng lấy 1 thẻ "Hoá Thân".')
	,('DUO001_1','Cầu An','Khi bạn nhận sát thương, nếu bạn không có thẻ "Hàm", bạn có thể đem thẻ bài gây sát thương cho bạn đặt lên võ tướng bài gọi là "Hàm", sau đó chặn sát thương này lại.')
	,('DUO001_2','Lượng Phản','Giai đoạn chuẩn bị, nếu võ tướng bài của bạn có "Hàm" thì bạn thu lấy "Hàm" sau đó tự giảm 1 sinh lực. Sau khi bạn dùng thẻ "Hàm" thu được trong lượt gây sát thương trong lượt này, bạn có thể thu lấy 1 thẻ bài của người nhận sát thương.')
	,('DUO002_1','Phong Thế','Sau khi bạn sử dụng bài chỉ định duy nhất 1 người khác, nếu bạn và họ đều có bài và họ có số bài trên tay ít hơn bạn, bạn có thể bỏ đi 1 thẻ của cả 2 người, sau đó sát thương này +1;\nSau khi bạn trở thành mục tiêu sử dụng bài duy nhất của người khác, nếu bạn và họ đều có bài và bạn có số bài trên tay ít hơn họ, họ có thể lệnh bạn bỏ đi 1 thẻ của cả 2 người, sau đó sát thương này +1.')
	,('DUO003_1','Tị Loạn','Người chơi khác tính toán khoảng cách đến bạn +X (X là số bài trong vùng Trang Bị của bạn).')
	,('DUO003_2','Lễ Hạ','Giai đoạn chuẩn bị của người khác không cùng thế lực với bạn, nếu bạn không nằm trong phạm vi công kích của họ, họ chọn 1 mục:\n\t1. Bỏ 1 thẻ ở vùng Trang Bị của bạn, sau đó họ giảm 1 sinh lực;\n\t2. Lệnh bạn rút 1 thẻ.')
	,('DUO004_1','Bố Thí','Sau khi bạn nhận 1 sát thương, bạn có thể rút 1 thẻ; Khi bạn gây sát thương cho người khác thế lực, họ có thể rút 1 thẻ, sau đó bạn rút 1 thẻ.')
	,('DUO004_2','Mễ Đạo','Giai đoạn hành động người cùng thế lực với bạn 1 lần, khi họ sử dụng thẻ [Sát] hoặc Cẩm Nang loại gây sát thương (không chuyển hoá) chỉ định mục tiêu đầu tiên, họ có thể giao cho bạn 1 thẻ trên tay, nếu là bạn thì bỏ qua bước này, rồi lệnh bạn gọi tên 1 chất bài và 1 loại thuộc tính. Sau đó thẻ bài họ sử dụng có chất và thuộc tính bài mà bạn đã gọi tên.')
	,('DUO005_1','Hưng Điệu','Bạn căn cứ theo số thế lực có người đang bị thương để đạt được hiệu quả tương ứng:\n\t≥ 1 - Bạn có kỹ năng 『Tuân Tuân』;\n\t≥ 2 - Sau khi bạn nhận sát thương, bạn cùng với nguồn sát thương so bài trên tay ai có ít bài hơn rút 1 thẻ bài;\n\t≥ 3 - Giới hạn trữ bài trên tay của bạn trong lượt +4;\n\t≥ 4 - Khi bạn mất đi bài trong vùng trang bị, bạn rút 1 thẻ.')
	,('DUO006_1','Truân Giang','Giai đoạn kết thúc, nếu bạn tại giai đoạn hành động lượt này đã sử dụng bài và chưa chỉ định người khác làm mục tiêu, bạn có thể rút X thẻ bài. (X là số thế lực trên bàn).')
	,('DUO006_2','Vấn Kế','Bắt đầu giai đoạn hành động, bạn có thể lệnh 1 người khác giao cho bạn 1 thẻ. Nếu người đó là Ẩn Danh/người cùng thế lực, bạn sử dụng thẻ đó lượt này không hạn chế khoảng cách lẫn số lần, đồng thời không thể phản ứng. Nếu họ không cùng thế lực, bạn phải giao lại 1 thẻ bài khác.')
	,('DUO007_1','Báo Liệt','Bắt đầu giai đoạn hành động, bạn lệnh cho tất cả người chơi khác không cùng thế lực có phạm vi tấn công đến bạn sử dụng 1 thẻ [Sát] lên bạn, nếu họ không thực hiện, bạn bỏ đi 1 thẻ của họ. Khi bạn dùng [Sát] lên người chơi có sinh lực ≥ sinh lực hiện tại của bạn, thẻ [Sát] đó của bạn không giới hạn khoảng cách và số lần.')
	,('DUO008_1','Thông Sát','Giai đoạn chuẩn bị, bạn có thể lựa chọn 1 người chơi chưa xác định thế lực(ẩn danh), cho đến khi bắt đầu lượt sau của bạn, sau khi họ lần đầu lật mở tướng, nếu họ cùng thế lực với bạn, bạn và họ rút 2 thẻ; Nếu không cùng thế lực với bạn, họ tự giảm 1 sinh lực. Giai đoạn rút bài, nếu tất cả người chơi đã xác định thế lực, bạn có thể rút thêm 2 thẻ.')
	,('DUO008_2','Công Thanh','Khi bạn nhận sát thương, nếu phạm vi công kích của nguồn sát thương nhỏ hơn 3, sát thương bạn nhận còn 1. Nếu phạm vi công kích của nguồn sát thương lớn hơn 3, sát thương này +1.')
	,('DUO009_1','Liên Phiên','Giai đoạn kết thúc, nếu trong lượt này bạn đã bỏ số bài của bất kỳ người chơi nào nhiều hơn số sinh lực hiện tại của bạn, bạn có thể lệnh 1 người chơi cùng thế lực bổ sung bài trên tay đến X (X là giới hạn sinh lực của họ).\nGiai đoạn kết thúc của người chơi khác, nếu trong lượt đó họ đã bỏ số bài của bất kì người chơi nào nhiều hơn số sinh lực hiện tại của bạn, họ có thể bỏ 1 thẻ của bạn hoặc lệnh bạn hồi phục 1 sinh lực.')
	,('DUO010_1','Thị Tài','Sau khi bạn nhận sát thương, nếu như sát thương là: Là 1, bạn rút 1 thẻ; nhiều hơn 1, bạn bỏ 2 thẻ.')
	,('DUO010_2','Thành Lược','Sau khi kết toán bài do 1 người cùng thế lực sử dụng, nếu số mục tiêu của thẻ đó lớn hơn 1, bạn có thể lệnh họ rút 1 thẻ. Sau đó, nếu bạn đã nhận sát thương từ thẻ bài này, bạn có thể lệnh 1 người cùng thế lực với bạn không có tướng úp và chưa có tiêu ký nào nhận 1 tiêu ký [Khuyết Ngọc].')
	,('DUO011_1','Căng Phạt','Một lần trong giai đoạn hành động, bạn có thể bỏ 1 thẻ, lệnh 1 người chơi khác có bài lựa chọn 1 mục:\n\t1. Bạn thu lấy 1 thẻ bài của họ;\n\t2. Giao cho bạn 1 thẻ Trang Bị, nếu thẻ trang bị được giao có chất ♠️, xem như họ sử dụng thẻ [Sát] lên bạn.')
	,('DUO012_1','Thông Lệnh','Giai đoạn hành động 1 lần, sau khi bạn gây sát thương đối với 1 người không cùng thế lực, bạn có thể chọn 1 người cùng thế lực với bạn, sau đó người cùng thế lực đó có thể sử dụng 1 thẻ đối với họ. Sau khi kết toán thẻ bài này, nếu: Thẻ đó có gây sát thương, bạn cùng người cùng thế lực rút 2 thẻ, nếu không họ thu lấy thẻ mà bạn đã dùng để gây sát thương lên họ.')
	,('DUO012_2','Cận Du','Sau khi bạn lật mở thẻ tướng này, bạn lệnh tất cả người chơi nằm trong khoảng cách 1 với bạn chấp hành hiệu quả sau: Nếu tất cả tướng họ đều lật mở, họ úp 1 thẻ tướng, nếu không họ tự bỏ đi 2 thẻ.')
	,('DUO013_1','Yến Ngữ','Giai đoạn hành động giới hạn 2 lần, bạn có thể Trọng Chú 1 thẻ [Sát] (không chuyển hóa). Nếu bạn làm thế, lượt này bạn tính toán khoảng cách đến người khác -1. Giai đoạn kết thúc, bạn có thể chọn tối đa X người chơi lệnh mỗi người rút 1 thẻ. (X là số lần Trọng Chú bởi "Yến Ngữ").')
	,('DUO013_2','Tiều Thập','Sau khi người khác cùng thế lực với bạn nhận sát thương, họ có thể giao cho bạn 1 thẻ [Sát] (không chuyển hóa), sau đó bạn có thể lệnh họ rút 1 thẻ.')
	,('DUO014_1','Oán Cừu','Trong lượt của bạn, thẻ [Sát] của bạn vô hiệu phòng cụ của người không cùng thế lực; Ngoài lượt của bạn, thẻ [Sát] sắc Đen của người không cùng thế lực vô hiệu phòng cụ của bạn.')
	,('DUO014_2','Quyết Sinh','Bắt đầu giai đoạn hành động, bạn có thể chọn 1 người khác và thu của họ 1 thẻ trên tay. Sau đó, bạn lệnh cho giới hạn sử dụng [Sát] ở lượt này +1 và nhận 1 sát thương từ họ. Nếu bằng cách này, bạn tiến vào trạng thái hấp hối, bạn rút 2 thẻ và họ rút 1 thẻ.')
	,('DUO015_1','Tập Đan','Sau khi bạn nhận sát thương, bạn bỏ 1 thẻ và rút 2 thẻ, sau đó bạn lật 1 thẻ từ chồng bài rút và đặt lên thẻ tướng này, gọi là "Đan" (thẻ "Đan" luôn mở). Trong cùng 1 lượt, nếu bạn hồi phục sinh lực 3 lần, bạn lập tức trận vong.')
	,('DUO015_2','Luyện Hóa','Giai đoạn chuẩn bị giới hạn 1 lần, bạn có thể di trừ 3 "Đan" cùng màu. Nếu bằng cách này: 1. "Đan" di trừ là sắc Đỏ, bạn thu lấy kĩ năng "Anh Tư"; 2. "Đan" di trừ là sắc đen, bạn thu lấy kĩ năng "Bế Nguyệt". Sau khi có người phát động "Anh Tư"/"Bế Nguyệt" cùng tên với kĩ năng bạn đang có, bạn có thể di trừ 1 "Đan" sau đó sử dụng 1 thẻ trên tay như [Hỏa Công]/[Quyết Đấu] tương ứng.')
	,('DUO016_1','Đao Hồn','Nếu trong vùng trang bị của bạn không có Vũ Khí, và trên bàn không có [Thanh Long Yểm Nguyệt Đao], bạn xem như trang bị thẻ đó. Khi thẻ [Thanh Long Yểm Nguyệt Đao] rời khỏi vùng chơi của người khác và tiến vào chồng bài bỏ, bạn thu lấy thẻ đó.')
	,('DUO016_2','Trung Dũng','Giai đoạn hành động hạn 1 lần, sau khi bạn gây sát thương cho mục tiêu bởi thẻ [Sát], bạn có thể:\n\t1. Bỏ 1 thẻ Vũ Khí, sau đó rút 2 thẻ\n\t2. Giao 1 thẻ Vũ Khí trên tay cho người khác')
	,('DUO017_1','Bút Phạt','Giai đoạn kết thúc, bạn có thể đem 1 thẻ trên tay giao mở cho 1 người khác, bắt đầu giai đoạn hành động tiếp theo của họ, nếu họ nhiều bài trên tay hơn bạn, bạn gây cho họ 1 sát thương, lượt này bạn không thể gây sát thương lên họ nữa.')
	,('DUO017_2','Tụng Từ','Giai đoạn hành động hạn 1 lần, bạn có thể chọn người khác, sau đó chọn 1 mục:\n\t1. Bạn bỏ của họ 2 thẻ, sau đó lệnh họ lấy 1 [Chu Liên];\n\t2. Bạn bỏ của họ 1 tiêu ký Quốc Chiến, sau đó lệnh họ rút 2 thẻ. Nếu kết thúc giai đoạn này họ không rơi vào trạng thái hấp hối, họ gây cho bạn 1 điểm sát thương.')
	,('DUO018_1','Lữ Lực','Mỗi lượt giới hạn 1 lần, khi bạn rơi vào trạng thái hấp hối, bạn có thể lật mở 2 thẻ trên đầu chồng bài, sau đó bạn có thể sử dụng trong số đó 1 thẻ sắc Đỏ xem như [Đào], và bạn đặt thẻ còn lại lên đỉnh chồng bài rút.')
	,('DUO018_2','Bối Thuỷ','Khi bạn đang kết toán sát thương, bạn và đối phương đều xem như trong trạng thái "Ác Chiến", người khác trừ bạn và họ đều không thể sử dụng [Đào].');

INSERT INTO HeroSkillTag (skill_id, skill_tag_id) VALUES
	('WEI017_2', 1),
	('WEI017_2', 6),
	('WEI017_3', 2),
	('WEI018_2', 5),
	('WEI018_3', 3),
	('WEI022_2', 3),
	('WEI023_2', 4),
	('WEI027_2', 2),
	('WEI027_2', 6),
	('WEI032_2', 3),
	('SHU002_1', 3),
	('SHU003_1', 3),
	('SHU004_2', 4),
	('SHU006_1', 3),
	('SHU008_1', 3),
	('SHU010_1', 3),
	('SHU011_1', 3),
	('SHU012_1', 3),
	('SHU013_1', 3),
	('SHU017_2', 1),
	('SHU017_2', 5),
	('SHU017_3', 2),
	('SHU017_3', 6),
	('SHU023_1', 4),
	('SHU024_2', 3),
	('SHU027_1', 3),
	('SHU031_2', 1),
	('SHU031_3', 2),
	('SHU031_3', 6),
	('SHU032_1', 3),
	('SHU032_2', 5),
	('SHU033_2', 1),
	('SHU033_2', 6),
	('SHU033_3', 3),
	('SHU035_2', 1),
	('SHU035_2', 6),
	('SHU037_2', 2),
	('SHU037_2', 3),
	('SHU037_2', 6),
	('WU003_1', 3),
	('WU005_1', 3),
	('WU010_1', 3),
	('WU012_1', 3),
	('WU013_1', 3),
	('WU017_2', 5),
	('WU019_3', 2),
	('WU019_3', 6),
	('WU021_2', 4),
	('WU024_2', 1),
	('WU024_2', 6),
	('WU027_1', 3),
	('WU029_1', 2),
	('WU033_1', 3),
	('WU037_2', 5),
	('QUN003_1', 4),
	('QUN003_2', 3),
	('QUN003_3', 3),
	('QUN004_1', 3),
	('QUN007_1', 3),
	('QUN010_1', 3),
	('QUN013_2', 4),
	('QUN014_1', 3),
	('QUN015_1', 3),
	('QUN016_1', 3),
	('QUN019_2', 1),
	('QUN019_2', 3),
	('QUN019_3', 3),
	('QUN020_2', 5),
	('QUN021_1', 4),
	('QUN023_1', 3),
	('QUN024_1', 3),
	('QUN025_1', 3),
	('QUN027_2', 4),
	('QUN031_2', 3),
	('QUN033_3', 4),
	('QUN034_1', 3),
	('QUN034_2', 5),
	('QUN034_3', 1),
	('QUN034_3', 6),
	('QUN035_1', 3),
	('QUN036_1', 1),
	('QUN036_1', 6),
	('QUN037_1', 3),
	('QUN037_2', 2),
	('QUN037_2', 3),
	('QUN037_2', 6);

INSERT INTO HeroCombo (hero_1_id, hero_2_id) VALUES
	('WEI001', 'WEI005'),
	('WEI001', 'WEI006'),
	('WEI001', 'WEI022'),
	('WEI002', 'WEI013'),
	('WEI002', 'WEI023'),
	('WEI002', 'WEI029'),
	('WEI003', 'WEI034'),
	('WEI005', 'WEI001'),
	('WEI006', 'WEI001'),
	('WEI006', 'WEI033'),
	('WEI007', 'WEI008'),
	('WEI007', 'WEI024'),
	('WEI008', 'WEI007'),
	('WEI009', 'WEI020'),
	('WEI010', 'WEI030'),
	('WEI011', 'WEI021'),
	('WEI012', 'WEI019'),
	('WEI013', 'WEI002'),
	('WEI014', 'WEI018'),
	('WEI018', 'WEI014'),
	('WEI019', 'WEI012'),
	('WEI020', 'WEI009'),
	('WEI021', 'WEI011'),
	('WEI022', 'WEI001'),
	('WEI023', 'WEI002'),
	('WEI024', 'WEI007'),
	('WEI027', 'WEI028'),
	('WEI028', 'WEI027'),
	('WEI029', 'WEI002'),
	('WEI030', 'WEI010'),
	('WEI031', 'WEI032'),
	('WEI032', 'WEI031'),
	('WEI033', 'WEI006'),
	('WEI034', 'WEI003'),
	('SHU001', 'SHU005'),
	('SHU001', 'SHU006'),
	('SHU001', 'SHU009'),
	('SHU001', 'SHU024'),
	('SHU002', 'SHU007'),
	('SHU003', 'SHU004'),
	('SHU003', 'SHU013'),
	('SHU003', 'SHU033'),
	('SHU003', 'SHU037'),
	('SHU004', 'SHU003'),
	('SHU005', 'SHU001'),
	('SHU005', 'SHU027'),
	('SHU006', 'SHU001'),
	('SHU007', 'SHU002'),
	('SHU007', 'SHU037'),
	('SHU008', 'SHU019'),
	('SHU008', 'SHU034'),
	('SHU009', 'SHU001'),
	('SHU010', 'SHU013'),
	('SHU010', 'SHU017'),
	('SHU010', 'SHU018'),
	('SHU010', 'SHU029'),
	('SHU011', 'SHU012'),
	('SHU012', 'SHU011'),
	('SHU013', 'SHU003'),
	('SHU013', 'SHU010'),
	('SHU014', 'SHU015'),
	('SHU015', 'SHU014'),
	('SHU017', 'SHU010'),
	('SHU018', 'SHU010'),
	('SHU018', 'SHU023'),
	('SHU019', 'SHU008'),
	('SHU023', 'SHU018'),
	('SHU024', 'SHU001'),
	('SHU027', 'SHU005'),
	('SHU029', 'SHU010'),
	('SHU031', 'SHU032'),
	('SHU032', 'SHU031'),
	('SHU032', 'SHU035'),
	('SHU033', 'SHU003'),
	('SHU033', 'SHU037'),
	('SHU034', 'SHU008'),
	('SHU035', 'SHU032'),
	('SHU037', 'SHU003'),
	('SHU037', 'SHU007'),
	('SHU037', 'SHU033'),
	('WU001', 'WU005'),
	('WU001', 'WU030'),
	('WU002', 'WU023'),
	('WU003', 'WU007'),
	('WU003', 'WU009'),
	('WU003', 'WU019'),
	('WU005', 'WU001'),
	('WU005', 'WU017'),
	('WU006', 'WU021'),
	('WU007', 'WU003'),
	('WU008', 'WU018'),
	('WU009', 'WU010'),
	('WU009', 'WU019'),
	('WU010', 'WU003'),
	('WU010', 'WU009'),
	('WU011', 'WU019'),
	('WU013', 'WU024'),
	('WU013', 'WU028'),
	('WU016', 'WU021'),
	('WU017', 'WU005'),
	('WU018', 'WU008'),
	('WU019', 'WU003'),
	('WU019', 'WU009'),
	('WU019', 'WU011'),
	('WU021', 'WU006'),
	('WU021', 'WU016'),
	('WU023', 'WU002'),
	('WU024', 'WU013'),
	('WU028', 'WU013'),
	('WU029', 'WU035'),
	('WU030', 'WU001'),
	('WU031', 'WU032'),
	('WU032', 'WU031'),
	('WU035', 'WU029'),
	('QUN002', 'QUN005'),
	('QUN002', 'QUN027'),
	('QUN003', 'QUN021'),
	('QUN003', 'QUN023'),
	('QUN005', 'QUN002'),
	('QUN007', 'QUN008'),
	('QUN008', 'QUN007'),
	('QUN011', 'QUN024'),
	('QUN017', 'QUN022'),
	('QUN019', 'QUN033'),
	('QUN021', 'QUN003'),
	('QUN022', 'QUN017'),
	('QUN023', 'QUN003'),
	('QUN024', 'QUN011'),
	('QUN027', 'QUN002'),
	('QUN031', 'QUN032'),
	('QUN032', 'QUN031'),
	('QUN033', 'QUN019');