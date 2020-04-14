$(function() {
  //現在日時を取得
  var current = new Date();

  var year_val = current.getFullYear();
  var month_val = current.getMonth() + 1;
  var day_val = current.getDate();

  //optionの中身を格納

  for (var i = 0; i < 1462; i++) {

    var date = new Date(year_val, month_val - 1, day_val + i);

    var y = date.getFullYear() - 4;
    var m = date.getMonth() + 1;
    var d = date.getDate();

    $('select[name=date]').append('<option value="' + y + '-' + m + '-' + d + '">' + y + '-' + m + '-' + d + '</option>');

    $('select[name=date]').val(year_val + '-' + month_val + '-' + day_val);

  }
});