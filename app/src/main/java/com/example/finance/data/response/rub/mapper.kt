package com.example.finance.data.response.rub

fun convertHashMapToList(hashMap: HashMap<String, Currency>): List<List<Currency>> {
    val list: MutableList<List<Currency>> = mutableListOf()

    // Преобразуем ключи HashMap в список и сортируем их
    val sortedKeys = hashMap.keys.sorted()

    // Итерируемся по каждому ключу и создаем список внутри списка
    for (key in sortedKeys) {
        val innerList: MutableList<Currency> = mutableListOf()

        // Добавляем текущий ключ и соответствующее значение во внутренний список
        innerList.add(Currency(key, hashMap[key]?.numCode ?: "", hashMap[key]?.charCode ?: "",
            hashMap[key]?.nominal ?: 0, hashMap[key]?.name ?: "",
            hashMap[key]?.value ?: 0f, hashMap[key]?.prev ?: 0f))

        // Добавляем внутренний список в основной список
        list.add(innerList)
    }

    return list
}
