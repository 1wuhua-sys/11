'''
i=1
while i<10:
    j=1
    while j<=i:
        k=i*j
        print(j,'*',i,'=',j*i,"",end=" ")
        j+=1
    print( )
    i+=1

list_a=[1,2,3,4,5,6]
list_a=list_a[::-1]
print(list_a)

mar=[[1,2,3],
     [4,5,6],
     [7,8,9]]
sum=0
for i in range(3):
    sum+=mar[i][i]
for i in range(3):
    sum+=mar[i][2-i]
sum-=mar[1][1]
print(sum)
'''


def get_dressing_advice(temperature, is_raining):
    if is_raining:
        advice = "今天下雨，请携带雨伞。"
        if temperature < 10:
            advice += "建议穿暖和点的衣服，比如羽绒服或大衣。"
        elif 10 <= temperature < 20:
            advice += "建议穿着长袖衣物，并考虑带一件外套。"
        else:
            advice += "轻薄的夹克或风衣就足够了。"
    else:
        if temperature < 10:
            advice = "今天温度较低，请穿上保暖的衣物，如毛衣、羽绒服或大衣。"
        elif 10 <= temperature < 20:
            advice = "今天的天气比较凉爽，建议穿着长袖T恤或衬衫加外套。"
        elif 20<=temperature <30:
            advice = "今天的天气温暖，可以选择短袖T恤或背心，搭配牛仔裤或休闲裤。"
        else:
            advice ="今天天气炎热，可以选择短裤裙子凉鞋，做好防晒。"
    return advice

if __name__ == "__main__":
    temperature = int(input("请输入当前温度（摄氏度）："))
    is_raining = input("是否下雨？（yes/no）").strip().lower() == 'yes'
    advice = get_dressing_advice(temperature, is_raining)
    print(advice)