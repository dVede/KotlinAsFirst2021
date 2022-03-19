# Summary

Если говорить о результатах покрытия, то написанные тесты полностью покрыли тестируемые методы.  Однако, сложности при выполнении данного задания возникли со следующим:
- Изначально хотелось воспользоваться Kover (плагин Gradle для покрытия нативного кода Kotlin), но, к сожалению, это невозможно сделать с Maven проектом.
- Использовать Jacoco также не удалось (Kotest интегрируется с Jacoco для покрытия кода, но у меня почему-то не вышло это сделать).
- Так как другие инструменты использовать не удалось, то пришлось использовать стандартный инструмент покрытия кода IntelIJ IDEA (результаты покрытия он порождает не самые красивые)
- Ещё одна сложность возникла с тем, чтобы определить классы и пакеты, которые будут добавлены в отчет. К сожалению, данную функциональность найти для тестов kotest не удалось (у junit тестов это делается довольно просто). Поэтому в отчете о покрытии информация обо всех пакетах и классах.