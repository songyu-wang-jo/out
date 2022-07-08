import openpyxl

if __name__ == '__main__':
    file_name = 'C:\\Users\\wsy\\Desktop\\医院业务库问题数据列表(测试)_20220704141012.xlsx'
    workbook = openpyxl.load_workbook(file_name)
    for sheetName in workbook.sheetnames:
        worksheet = workbook[sheetName]
        print(worksheet['B1'].value)
