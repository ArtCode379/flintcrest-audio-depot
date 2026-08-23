You are running the implementation phase for one Openclaw Android app.

Use these orchestrator instructions as binding context: /home/codex-agent/codex-app-agent/AGENTS.md
Use this screen spec: /home/codex-agent/codex-app-agent/screens-shop.md
Project directory: /tmp/flintcrest-audio-depot

Task metadata:
- Asana task gid: 1217481999937457
- Asana task name: GB GW4 C1526
- Asana URL: https://app.asana.com/1/1208304498069546/project/1213586227413017/task/1217481999937457
- App name: Flintcrest Audio Depot
- Company: FLINTCREST TRADE LIMITED
- Domain: http://flintcrest-trade.surf/
- Package: flintcresttrade.musical.flintcrestaudiodepot
- Prefix: WHDBN
- Type: shop
- Description: Специфика компании - торговля музыкальными инструментами и аудиооборудованием. Приложение по продаже товаров компании, содержит список всех товаров компании (гитары, клавишные, ударные, микрофоны, наушники, колонки и аудиоинтерфейсы; с возможностью сортировки по категориям). История покупок. Корзина товаров с формой бронирования заказа. После подтверждения бронирования пользователь видит баннер с информацией о номере и деталях заказа с уведомлением о том, что его ожидают в магазине в течении 24 часов. Настройки должны содержать информацию о: названии компании, версии приложения, линку Customers Support со ссылкой сайт компании.  
(можно разнообразить главную страницу каруселью с заметками или статьями о выборе музыкальных инструментов и настройке аудиооборудования)

Do Phase 2 and Phase 3 only:
1. Extract or derive the style guide.
2. Do not create project-local agent instruction files inside /tmp/flintcrest-audio-depot.
3. Implement all required screens/content/data/assets/icon according to the orchestrator AGENTS.md and the screen spec.
4. Icon generation is best-effort: if Leonardo/imagegen cannot provide a filesystem-backed icon quickly, continue implementing the app with existing assets.
5. Do not push to GitHub, do not update Asana, and do not send Slack.
6. You may run local checks while implementing, but the runner will run quality/build afterward.
7. Keep every Kotlin file conventionally formatted: one statement per line, annotations above declarations, expanded indented Compose blocks, no semicolon-compressed code, and no source line longer than 200 characters.
