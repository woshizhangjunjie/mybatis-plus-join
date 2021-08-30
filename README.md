# mybatis-plus-join

#目前仅支持两张表的关联,以及常用的条件查询,用于敏捷开发能大大提升开发效率

#方法名变量名中有left为关联时候左边的表,right则为右边的表

#注:如sql的复杂度很高,切勿使用

```
        PageHelper.startPage(0,9);//目前只支持pageHelper分页插件
        JoinWrapper<User, Wallet> joinWrapper = new JoinWrapper<>(Wallet.class);//泛型必填 构造器中的类型为被关联表
        joinWrapper.on(User::getId, Wallet::getUserId);//键关联
        joinWrapper.rightBetween(Wallet::getTotal, 2,11);//被关联表的between筛选
        joinWrapper.leftOrderByAsc(User::getId,User::getName);//主表的排序
        List<User> users = userMapper.selectJoin(joinWrapper);
```
