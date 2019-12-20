$(function() {
   let table = $('#dataTable').DataTable();

   $('#dataTable_filter')
         .prepend(
               '<select id="select" class="custom-select" style="margin-right : 10px; width: 100px"></select>');

   // 검색 th 칼럼 별로 할 수 있게 select 생성
   let ths = $('#dataTable > thead > tr > th');
   ths.each(function(index, element) {
      if (index < 2) // 앞에 두개만
         $('#select').append('<option>' + element.innerHTML + '</option>');
   });

   $("#deptSelect").change(function() {
      tableSearch();
   })

   function tableSearch() {
      let colIndex = document.querySelector('#select').selectedIndex;
      let searchText = $('.dataTables_filter input').val();
      
      table.column(colIndex).search(searchText).draw();
   }
});