grammar QCon;

prog: expr (';' expr)* ;

expr: op=('+'|'-') expr                 #Unary
    | left=expr op=('*'|'/') right=expr #Op
    | left=expr op=('+'|'-') right=expr #Op
    | NUMBER                            #Literal
    | ID                                #GetValue
    | ID '=' expr                       #Assign
    |  '(' expr ')'                     #Parens
    ;

ID: [a-z] [a-zA-Z0-9]*;
NUMBER: [0-9]+;
WS: [ \t\n]+ -> skip;

// handle characters which failed to match any other token
UnknownToken : .;
