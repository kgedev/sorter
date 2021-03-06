Агоритм не чувствителен к спискам содержащим null значения. Такие значения игнорируются. Смещение индексов не происходит.

Алгоритм работает по следующему принципу:
   - все элементы из передаваемой коллекции кэшируются. В качестве кэша используется HashMap<Integer, Integer>,
   в которой в качестве ключа используется индекс элемента из переданной коллекции, а в качестве
   значения - значение номера элемента.
   При кэшировании создается 2 коллекции Map<Integer, Integer>
   1 - map actualElementsOrder с текущим порядком элементов(причем в кэш не попадают значения элементов, 
   которые уже находятся на нужных местах и null элементы)
   2 - map expectedElementsOrder с ожидаемым порядком элементов(не попадают null элементы).

   алгоритм сравнивает значения из кэша expectedOrder с текущими, и если они не совпадают, то заменяет их.
   
   I - определяются вспомогательные переменные необходимые для работы алгоритма:
        stub - заглушка, рандомное число, которое не содержит ни один IElement в качестве значения, необходима для начала замены значений.
        tmp = временная переменная которая хранит в себе значение IElement.getNumber()
        elementIdx - индекс элемента для которого нужно получить или установить значение поля number.
        
   II - пока кэш actualElementsOrder не пустая выполняется следующее
        1 - проверяется вернулись ли мы в то место с которого начали присвоение
        Если да:
            1.1 Получаем первый элемент из кэша actualElementsOrder
            1.2 сохраняем значение элемента находящегося по индексу из п.1.1 в tmp
            1.3 присваиваем элементу в поле number значение заглушки
            1.4 дублируем присвоение в кэше actualElementsOrder
        Если нет:
            1.5 получаем индекс элемента, в котором должно находиться значение из tmp
            1.6 получаем значение элемента с индексом из п.1.5 и сохраняем его в tmp
            1.7 устанавливаем для элемента с индексом из п.1.5 значение, 
            которое находится в кэше expectedElementsOrder под индексом из п.1.5
            1.8 удаляем из кэша значение с индексом из п.1.5
        2 - возвращаемся в II
 
   * Количество вызовов метода IElement.setupNumber для:
       - в лучшем случае - равно количеству элементов входящих в эту группу + 1 - происходит всего одно лишнее 
       присвоение в самом начале, затем элементы меняются местами
       - в худшем случае - равно количеству элементов входящих в эту группу * 1.5 - кода элементы имеют зеркальную позицию,
       тоесть например текущий порядок [15, 10, 30, 20] а ожидаемый [10, 15, 20, 30] - здесь нужно поменять местами 
       arr[0]=15, arr[1]=10 но ожидается arr[0]=10 a arr[1]=15 - для таких элементов, у которых в 
       кэшах actualElementsOrder и expectedElementsOrder пары индексов равны, а значения зеркальны, 
       требуется одна дополнительная замена т.е на 4 таких элемента(2 пары) 6 замен или 6/4 = 1.5
       
    
       