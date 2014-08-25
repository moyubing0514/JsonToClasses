/**
 *
 *MESSAGE {MESSAGE_NAME}
 *example
 *{
 *{{PROP_EXAMPLE}
 *}
**/

@implementation {MESSAGE_NAME}

{PROP_SYNTHESIZE}


- (id)init
{
    return [super init];
}

//json字典赋值
-(id)initWithJson:(NSDictionary*)json
{
    self = [self init];
    {PROP_ASSIGNMENT}
    return self;
}
-(NSString*)toJsonString
{
    NSDictionary *dic = [NSDictionary dictionaryWithObjectsAndKeys:
                       {PROP_DIC}
                        nil ];
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dic options:NSJSONWritingPrettyPrinted error:&error];
    NSString *json =[[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    return json;
}
@end