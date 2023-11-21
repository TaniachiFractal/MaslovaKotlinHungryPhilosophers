fun main()
{
    println("Вы открыли столовую для философов.\n")
    var daysPassed = 0
    while (true) {
        println("\nДень ${++daysPassed}.\n")
        var eatCount = 0
        var thinkCount = 0
        var tmpStr: String
        namesArray.shuffle()  //mix them up
        print(
            "За дверью Вашей столовой стоит ${namesArray.size} философ${ru2typeMascEnding(namesArray.size)}. Они очень задумчивые и голодные\n" +
                    "Сколько пустить в столовую? \n> "
        )
        var philCount = readln().toInt()
        philCount-- //array starts from 0, but we want 0th phil to show up when we say "1 phil"
        if (philCount > namesArray.size) philCount = namesArray.size - 1
        if (philCount < 2) philCount = 2
        println("${philCount + 1} философ${ru2typeMascEnding(philCount + 1)} сели за круглый стол.")
        val forks = Array<Boolean>(philCount + 1) { _ -> true }   //phil's actual fork is on the right
        print("Их зовут: ")
        for (i in 0..philCount) {
            print("${i + 1} - ${namesArray[i]}")
            if (i < philCount) print(", ")
            else println(".")
            if (i % 10 == 9) println()
        }
        tmpStr = "Н"
        while (tmpStr == "Н") {
            println("Начать есть? [Д]а, начать/[Н]ет, пусть ждут")
            print("> ")
            tmpStr = readln().uppercase()
            if (tmpStr != "Д") tmpStr = "Н"
        }
        val todaysMeal = foodArray[(0..foodArray.size - 1).random()].lowercase() //write a random food
        println("Кушать подано! Сегодня на ужин философы будут есть $todaysMeal.")
        //generate the order they take forks in
        val orderArray = Array<Int>(philCount + 1) { i -> i }; orderArray.shuffle()
        for (i in orderArray) {
            var act = (0..1).random()    //0-right, 1-left
            print("${i + 1}-${namesArray[i]} пытается взять вилку с${L_or_R(act)}а. >> ")
            if (forks[post(i, act, philCount)]) {
                println("Ура! Вилка свободна. ${namesArray[i]} радостно кушает $todaysMeal")
                forks[post(i, act, philCount)] = false
                eatCount++
            } else {
                println("О, нет! Вилку кто-то взял. ${namesArray[i]} думает...")
                thinkCount++
            }
        }
        if (thinkCount > 0) {
            println(
                "В итоге $eatCount философ${ru2typeMascEnding(eatCount)} ест, а $thinkCount - размышляет.\n" +
                "Будем надеяться, что они додумаются, что можно спросить других дать им свободную вилку с другой стороны стола."
            )
        }
        else
        {
            println("Все вкусно поели. Замечательно!")
        }
        print("Закрыть столовую? [Д]а, закрыть/[Н]ет, пусть работает ещё 1 день. \n> ")
        if (readln().uppercase()=="Д") break
    }
    println("Философская столовая закрыта.")
}

fun ru2typeMascEnding(input: Int):String //what ending does a number of 2nd type masc nouns have
// (ex. 1 кот[], 2 кот[a], 5 кот[ов])
{
    if (input%100 in (11..14) ) return("ов")
    when (input%10)
    {
        1 -> return ""
        in (2..4) -> return "а"
        in (5..9) -> return "ов"
        0 -> return "ов"

    }
    return ""
}
fun post(a:Int,b:Int,size: Int):Int
{
   if (a+b>size) return 0
   return a+b
}
fun L_or_R(input:Int): String  //0 - left, 1 - right
{
    if (input==0)
    {
        return "прав"
    }
    else
    {
        return "лев"
    }

}
val namesArray = arrayOf("Денис","Андрей","Лариса","Наталья","Владлен",
    "Максим","Александр","Юрий","Семён","Софья","Мария","Алиса","Фёдор","Таня",
    "Сьюзан","Эвелин","Эрик","Уан","Памела","Ева","Юля","Тёма","Рома","Генри","Дарк","Мазай",
    "Линда","Ричард","Уильям","Анатолий", "Бренда","Ширли","Кевин","Джорж","Григорий",
    "Ярослава","Варвара","Людмила","Дмитрий","Владимир","Тимофей","Матвей"
    ) //some various names in russian
val foodArray = arrayOf("Курицу", "Гречку", "Жаркое", "Макароны","Котлетки с пюрешкой","Сосиски")