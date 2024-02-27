## 啟動說明

1. clone

   ```
   git clone https://github.com/WUNDSOR/exam.git
   ```

2. 用 IDE 開啟專案並啟動。

## API說明

1. 測試呼叫查詢幣別對應表資料 API，並顯示其內容。

   ```
   /api/coin/queryByType
   ```

   | 參數 | 說明 |
   | ---- | ---- |
   | type | 幣別 |

   > 方法：GET

2. 測試呼叫新增幣別對應表資料 API。

   ```
   /api/coin/addOne
   ```

   | 參數 | 說明   |
   | ---- | ------ |
   | type | 幣別   |
   | name | 中文名 |

   > 方法：POST application/json

3. 測試呼叫更新幣別對應表資料 API，並顯示其內容。

   ```
   /api/coin/updateByType
   ```

   | 參數 | 說明   |
   | ---- | ------ |
   | type | 幣別   |
   | name | 中文名 |

   > 方法：POST application/json

4. 測試呼叫刪除幣別對應表資料 API。

   ```
   /api/coin/removeByType
   ```

   | 參數 | 說明 |
   | ---- | ---- |
   | type | 幣別 |

   > 方法：POST application/json

5. 測試呼叫 coindesk API，並顯示其內容。

   ```
   /api/currentprice/v1
   ```

   | 參數 | 說明 |
   | ---- | ---- |
   | 無   |      |

   > 方法：GET

6. 測試呼叫資料轉換的 API，並顯示其內容。

   ```
   /api/currentprice/v2
   ```

   | 參數 | 說明 |
   | ---- | ---- |
   | 無   |      |

   > 方法：GET